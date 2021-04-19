package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Alex Gutorov"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Alex Gutorov"));
    }

    @Test
    public void whenPairIsEmpty() {
        String path = "pair_empty.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("name"));
    }
}