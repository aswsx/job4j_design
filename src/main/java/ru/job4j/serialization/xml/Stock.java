package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "Stock")
@XmlAccessorType(XmlAccessType.FIELD)
public class Stock {
    @XmlAttribute
    private boolean availability;
    @XmlAttribute
    private int quantity;
    private Detail detail;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Stock() {
    }

    public Stock(boolean availability, int quantity, Detail detail, String... statuses) {
        this.availability = availability;
        this.quantity = quantity;
        this.detail = detail;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Stock{"
                + "availability=" + availability
                + ", quantity=" + quantity
                + ", detail=" + detail
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        final var stock = new Stock(true, 25, new Detail("Part", "01210203"), "reserved", "limited");

        var context = JAXBContext.newInstance(Stock.class);
        var marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        var xml = "";

        try (var writer = new StringWriter()) {
            marshaller.marshal(stock, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        var unmarshall = context.createUnmarshaller();
        try (var reader = new StringReader(xml)) {
            Stock result = (Stock) unmarshall.unmarshal(reader);
            System.out.println(result);
        }
    }
}