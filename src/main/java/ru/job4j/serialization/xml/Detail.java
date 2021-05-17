package ru.job4j.serialization.xml;

public class Detail {
    private final String name;
    private final String partNumber;

    public Detail(String name, String partNumber) {
        this.name = name;
        this.partNumber = partNumber;
    }

    @Override
    public String toString() {
        return "Detail{"
                + "name='" + name + '\''
                + ", partNumber='" + partNumber + '\''
                + '}';
    }

}