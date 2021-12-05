package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {
    @Ignore
    @Test
    public void whenRightData() {
        String template = "I am a/an ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Alex");
        map.put("subject", "you");
        Generator generator = new PhraseGenerator();
        String result = generator.produce(template, map);
        String expected = "I am a/an Alex. Who are you?.";
        assertThat(result, is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenExtraArguments() {
        String template = "Hello, my name is ${name}. I am ${age} years old.";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Alex");
        map.put("age", "43");
        map.put("town", "Nur-Sultan");
        Generator generator = new PhraseGenerator();
        String result = generator.produce(template, map);
        String expected = "Hello, my name is Alex. I am 43 years old.";
        assertThat(result, is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenLostMapArgs() {
        String template = "Hello, my name is ${name}. I am ${age} years old. I live in ${town}.";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Alex");
        map.put("age", "43");
        Generator generator = new PhraseGenerator();
        String result = generator.produce(template, map);
        String expected = "Hello, my name is Alex. I am 43 years old.I live in Nur-Sultan";
        assertThat(result, is(expected));
    }
}