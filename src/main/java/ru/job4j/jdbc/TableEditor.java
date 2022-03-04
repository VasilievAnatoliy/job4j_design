package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void statement(String str, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(str);
            try {
                System.out.println(getTableScheme(connection, tableName));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    public void createTable(String tableName) throws Exception {
        statement(String.format("create table if not exists %s();", tableName), tableName);
    }

    public void dropTable(String tableName) throws Exception {
        statement(String.format("drop table %s;", tableName), tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        statement(String.format("alter table %s add %s %s;", tableName, columnName, type), tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        statement(String.format("alter table %s drop %s;", tableName, columnName), tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        statement(String.format("alter table %s rename %s to %s;",
                tableName, columnName, newColumnName), tableName);

    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("./src/main/resources/app.properties")) {
            properties.load(in);
            TableEditor table = new TableEditor(properties);
            table.createTable("demo_table");
            table.addColumn("demo_table", "age", "int");
            table.renameColumn("demo_table", "age", "number");
            table.dropColumn("demo_table", "number");
            table.dropTable("demo_table");
        }
    }
}
