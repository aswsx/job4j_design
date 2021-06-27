//package ru.job4j.jdbc;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Properties;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//public class TableEditorTest {
//    Properties properties = new Properties();
//    StringBuilder expected = new StringBuilder();
//
//    @Before
//    public void init() throws IOException {
//        URL pathSource = ClassLoader.getSystemResource("app.properties");
//        properties.load(new FileReader(pathSource.getPath()));
//    }
//
//    @Test
//    public void whenAddAndThenRenameColumn() throws Exception {
//        try (TableEditor editor = new TableEditor(properties)) {
//            editor.dropTable("testTable");
//            editor.createTable("testTable");
//            editor.addColumn("testTable", "id", "serial");
//            editor.addColumn("testTable", "name", "varchar(255)");
//            editor.renameColumn("testTable", "name", "surname");
//            expected.append(String.format("%-15s %-15s%n", "column", "type"));
//            expected.append(String.format("%-15s %-15s%n", "id", "serial"));
//            expected.append(String.format("%-15s %-15s%n", "surname", "varchar"));
//            assertThat(editor.getScheme("testtable"), is(expected.toString()));
//        }
//    }
//
//    @Test
//    public void whenAddAndThenDropColumn() throws Exception {
//        try (TableEditor editor = new TableEditor(properties)) {
//            editor.dropTable("testTable");
//            editor.createTable("testTable");
//            editor.addColumn("testTable", "id", "serial");
//            editor.addColumn("testTable", "name", "varchar(255)");
//            editor.dropColumn("testTable", "name");
//            expected.append(String.format("%-15s %-15s%n", "column", "type"));
//            expected.append(String.format("%-15s %-15s%n", "id", "serial"));
//            assertThat(editor.getScheme("testtable"), is(expected.toString()));
//        }
//    }
//
//    @Test
//    public void whenAddAndThenDropTable() throws Exception {
//        try (TableEditor editor = new TableEditor(properties)) {
//            editor.dropTable("testTable");
//            editor.createTable("testTable");
//            editor.addColumn("testTable", "id", "serial");
//            editor.addColumn("testTable", "name", "varchar(255)");
//            editor.dropTable("testTable");
//            assertThat(editor.getScheme("testtable"), is(String
//                    .format("%-15s %-15s%n", "column", "type")));
//        }
//    }
//}