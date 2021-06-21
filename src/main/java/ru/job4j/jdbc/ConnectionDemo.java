package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс - выполняет подключение базы данных postgres через jdbc.  *
 *
 * @author Alex Gutorov
 * @version 1.0
 * <p>
 */
public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        URL pathSource = ClassLoader.getSystemResource("app.properties");
        var prepare = new Prepare(pathSource.getPath());
        prepare.parse();
        try (var connection = DriverManager
                .getConnection(prepare.get("url"),
                        prepare.get("login"),
                        prepare.get("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }

    /**
     * Класс - выдергивает параметры (адрес, логин, пароль) из файла и подготавливает для передачи их в jdbc
     * <p>
     */
    private static class Prepare {
        private static final Logger LOG = LoggerFactory.getLogger(Prepare.class.getName());
        private final String path;
        private final Map<String, String> values;

        public Prepare(String path) {
            this.path = path;
            this.values = new HashMap<>();
        }

        /**
         * Метод считывает строки из принимаемого по пути файла и разбивает его на подстроки по знаку =
         * После чего записывает подстроки отдельно в мапу
         */
        private void parse() {
            try (var reader = new BufferedReader(new FileReader(path))) {
                reader.lines()
                        .forEach(s -> {
                            String[] line = s.split("=");
                            if (line.length == 2) {
                                values.put(line[0], line[1]);
                            }
                        });
            } catch (IOException e) {
                LOG.error("Wrong app.properties, e");
            }
        }

        /**
         * Метод возвращает значение по принятому ключу
         *
         * @param key принимаемый ключ
         * @return возвращает значение
         */
        public String get(String key) {
            return values.get(key);
        }
    }
}
