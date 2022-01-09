package ru.job4j.srp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Alex Gutorov
 * @version 1.0
 * Класс генерирует отчет в виде xml  с помощью JAXB
 */
public class XMLGenerator implements Report {
    private static final Logger LOG = LoggerFactory.getLogger(XMLGenerator.class.getName());

    private final Store store;

    public XMLGenerator(Store store) {
        this.store = store;
    }

    /**
     * Метод генерирует отчет в формате xml вызывая все элементы из спсика store и
     * передавая их в метод generateXML
     *
     * @param filter фильтр для выбора элементов
     * @return возвращаемый отчет в виде xml
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        var xml = "";
        try {
            xml = generateXml(store.findBy(filter));
        } catch (JAXBException jaxbe) {
            LOG.error("generate error", jaxbe);
        }
        return xml;
    }

    /**
     * @param emp - список сотрудников
     * @return возвращаемый текст в формате xml
     * @throws JAXBException родительское исключение JAXB
     *                       Метод генерирует xml
     *                       JAXBContext - точка входа в JAXB API, для получения возможности
     *                       использования marshaller
     *                       Marshaller - класс, отвечающий за сериализацию
     */
    private String generateXml(List<Employee> emp) throws JAXBException {
        var context = JAXBContext.newInstance(Employees.class);
        var marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        var xml = "";
        try (var writer = new StringWriter()) {
            marshaller.marshal(new Employees(emp), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException ioe) {
            LOG.error("generateXML error", ioe);
        }
        return xml;
    }

    /**
     * Класс- модель данных Employees
     */
    @XmlRootElement(name = "empList")
    public static class Employees {

        private List<Employee> empList;

        public Employees() {
        }

        public Employees(List<Employee> empList) {
            this.empList = empList;
        }

        public List<Employee> getEmpList() {
            return empList;
        }

        public void setEmpList(List<Employee> empList) {
            this.empList = empList;
        }
    }
}
