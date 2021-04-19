package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void serverLogAnalyze() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("300 08:21:01");
            out.println("300 08:57:01");
            out.println("400 08:58:01");
            out.println("400 09:59:01");
            out.println("500 10:01:02");
            out.println("500 10:02:02");
            out.println("300 10:56:01");
            out.println("400 10:57:01");
            out.println("300 10:58:01");
            out.println("200 10:59:01");
            out.println("400 12:01:02");
            out.println("400 14:02:02");
            out.println("500 14:21:01");
            out.println("200 14:59:02");
            out.println("300 15:11:01");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString(),
                is("08:58:01;10:56:01;10:57:01;10:58:01;12:01:02;14:59:02;"));
    }

    @Test
    public void unavailableTest() {
        Analizy analizy = new Analizy();
        URL pathSource = ClassLoader.getSystemResource("server.log");
        URL pathTarget = ClassLoader.getSystemResource("unavailable.csv");
        analizy.unavailable(
                pathSource.getPath(),
                pathTarget.getPath()
        );
        List<String> expected = List.of(
                "10:58:01;10:59:01;",
                "11:01:02;11:02:02;"
        );
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(pathTarget.getPath())
        )) {
            in.lines().forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(rsl, is(expected));
    }
}