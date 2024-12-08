package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void validateContains() {
        NameLoad nameLoad = new NameLoad();
        String name = "name:lastName";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol");
    }

    @Test
    void checkValidateStartsWith() {
        NameLoad nameLoad = new NameLoad();
        String name = "=name";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkValidateEndWith() {
        NameLoad nameLoad = new NameLoad();
        String name = "name=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value");
    }
}