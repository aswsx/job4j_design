package ru.job4j.collection;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnalizeTest {

    @Test
    public void whenDeleted() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Michael"),
                new Analize.User(2, "Nick"),
                new Analize.User(3, "Ivan")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Michael"),
                new Analize.User(3, "Ivan")
        );
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(0, 0, 1);
        assertThat(info, is(expected));
    }

    @Test
    public void whenAdded() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Michael"),
                new Analize.User(3, "Ivan")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Michael"),
                new Analize.User(2, "Nick"),
                new Analize.User(3, "Ivan")
        );
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(1, 0, 0);
        assertThat(info, is(expected));
    }

    @Test
    public void whenChanged() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Michael"),
                new Analize.User(2, "Max"),
                new Analize.User(3, "Ivan")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Michael"),
                new Analize.User(2, "Nick"),
                new Analize.User(3, "Ivan")
        );
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(0, 1, 0);
        assertThat(info, is(expected));
    }

    @Test
    public void whenAllActions() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Michael"),
                new Analize.User(2, "Max"),
                new Analize.User(3, "Ivan")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Nick"),
                new Analize.User(3, "Ivan"),
                new Analize.User(4, "Ivan")
        );
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(1, 1, 1);
        assertThat(info, is(expected));
    }

    @Test
    public void whenNoActions() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Michael"),
                new Analize.User(2, "Max"),
                new Analize.User(3, "Ivan")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Michael"),
                new Analize.User(2, "Max"),
                new Analize.User(3, "Ivan")
        );
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(0, 0, 0);
        assertThat(info, is(expected));
    }
}