package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;
    private static final String ALTER_TABLE = "alter table";

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        String sql = "create table if not exists " + tableName + "()";
        execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = "drop table if exists " + tableName;
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = ALTER_TABLE + " " + tableName + " add column " + columnName + " " + type;
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = ALTER_TABLE + " " + tableName + " drop column " + columnName;
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = ALTER_TABLE + " " + tableName + " rename column " + columnName + " to " + newColumnName;
        execute(sql);
    }

    public String getScheme(String tableName) throws SQLException {
        var scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData
                .getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    /**
     * избавляет от дублирования кода
     *
     * @param sql скрипт для выполнения
     */
    private void execute(String sql) throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }


    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}