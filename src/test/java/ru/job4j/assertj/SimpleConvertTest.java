package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).first().isEqualTo("first");
        /*элемент по индексу*/
        assertThat(list).element(3).isNotNull()
                .isEqualTo("four");
        /*последний элемент*/
        assertThat(list).last().isNotNull()
                .isEqualTo("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).isNotNull()
                /*все элементы выполняют условие*/
                .anyMatch("five"::equals)
                .noneMatch("six"::equals)
                .doesNotContain("zero")
                .containsAnyOf("first", "three");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "five")
                /*содержит значения*/
                .containsValues(1, 2, 3)
                /*не содержит ключ*/
                .doesNotContainKey("six")
                /*не содержит значение*/
                .doesNotContainValue(7)
                /*содержит пару ключ-значение*/
                .containsEntry("four", 3);
    }
}

