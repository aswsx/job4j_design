package ru.job4j.io;

import org.junit.Test;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        URL path = ClassLoader.getSystemResource("pair_without_comment.properties");
        Config config = new Config(path.getPath());
        config.load();
        assertThat(config.value("name"), is("Alex Gutorov"));
    }

    @Test
    public void whenPairWithComment() {
        URL path = ClassLoader.getSystemResource("pair_with_comment.properties");
        Config config = new Config(path.getPath());
        config.load();
        assertThat(config.value("name"), is("Alex Gutorov"));
    }

    @Test
    public void whenPairIsEmpty() {
        URL path = ClassLoader.getSystemResource("pair_empty.properties");
        Config config = new Config(path.getPath());
        config.load();
        assertNull(config.value("name"));
    }
}