package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties cfg;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (var rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                String[] str = s.split(";");
                if (str.length == 2) {
                    users.add(new User(
                            str[0],
                            str[1]
                    ));
                }
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (var connection = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement statement = connection.
                        prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    statement.setString(1, user.name);
                    statement.setString(2, user.email);
                    statement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        var cfg = new Properties();
        try (var in = new FileInputStream(ClassLoader.getSystemResource("app.properties").getPath())) {
            cfg.load(in);
        }
        var db = new ImportDB(cfg, ClassLoader.getSystemResource("dump.txt").getPath());
        db.save(db.load());
    }
}