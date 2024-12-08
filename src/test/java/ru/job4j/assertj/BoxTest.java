package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisExistsAndItIsSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotBlank()
                .isNotEmpty()
                .isEqualTo("Sphere");
    }

    @Test
    void ifCubeNumberOfVerticesIs8AndAreaIs() {
        Box box = new Box(8, 10);
        int verticesNumber = box.getNumberOfVertices();
        assertThat(verticesNumber)
                .isPositive()
                .isGreaterThan(6)
                .isEven()
                .isEqualTo(8);
    }

    @Test
    void ifCubeNumberOfVerticesIs() {
        Box box = new Box(8, 10);
        int verticesNumber = box.getNumberOfVertices();
        assertThat(verticesNumber)
                .isLessThan(10)
                .isNotZero()
                .isEqualTo(8);
    }

    @Test
    void ifNumberOfVerticesIs2ThenNotExist() {
        Box box = new Box(2, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("know")
                .doesNotContain("Tetrahedron")
                .startsWith("Un")
                .startsWithIgnoringCase("u")
                .endsWith("ject")
                .isEqualTo("Unknown object");
    }

    @Test
    void ifNumberOfVerticesIs4ThenTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("tetra")
                .doesNotContain("C")
                .startsWith("Te")
                .startsWithIgnoringCase("t")
                .endsWith("on")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void ifSphereThenAreaEqualsTo() {
        Box box = new Box(0, 17);
        double area = box.getArea();
        assertThat(area).isEqualTo(3631.68d, withPrecision(0.01d));
    }

    @Test
    void ifSphereThenAreaIsDouble() {
        Box box = new Box(0, 17);
        double area = box.getArea();
        assertThat(area)
                .isGreaterThan(3631d)
                .isNotZero()
                .isCloseTo(3631.7d, withPrecision(0.1d))
                .isCloseTo(3631d, Percentage.withPercentage(1))
                .isLessThan(3631.7d)
                .isEqualTo(3631.68d, withPrecision(0.01d));
    }
}