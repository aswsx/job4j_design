package ru.job4j.serialization.xml;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "Detail")
public class Detail {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String partNumber;

    public Detail() {
    }

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