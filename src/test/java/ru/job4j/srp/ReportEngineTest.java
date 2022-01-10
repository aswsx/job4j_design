package ru.job4j.srp;

import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        var store = new MemStore();
        var now = Calendar.getInstance();
        var worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        var engine = new ReportEngine(store);
        var expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + worker.getName()
                + ";"
                + worker.getHired()
                + ";"
                + worker.getFired()
                + ";"
                + worker.getSalary()
                + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenHRReportGenerated() {
        var store = new MemStore();
        var now = Calendar.getInstance();
        var firstWorker = new Employee("Ivan", now, now, 100);
        var secondWorker = new Employee("Nikolay", now, now, 150);
        var thirdWorker = new Employee("Michael", now, now, 80);
        store.add(firstWorker);
        store.add(secondWorker);
        store.add(thirdWorker);
        var engine = new HRReportEngine(store);
        var expect = "Name; Salary"
                + System.lineSeparator()
                + secondWorker.getName()
                + ";"
                + secondWorker.getSalary()
                + ";"
                + System.lineSeparator()
                + firstWorker.getName()
                + ";"
                + firstWorker.getSalary()
                + ";"
                + System.lineSeparator()
                + thirdWorker.getName()
                + ";"
                + thirdWorker.getSalary()
                + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenITReportGenerated() {
        var store = new MemStore();
        var now = Calendar.getInstance();
        var firstWorker = new Employee("Ivan", now, now, 100);
        var secondWorker = new Employee("Nikolay", now, now, 150);
        var thirdWorker = new Employee("Michael", now, now, 80);
        store.add(firstWorker);
        store.add(secondWorker);
        store.add(thirdWorker);
        var engine = new ITReportEngine(store);
        var expect = "<DOCTYPE html"
                + System.lineSeparator()
                + "<html lang =\"en\">"
                + System.lineSeparator()
                + "<body>"
                + System.lineSeparator()
                + "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + firstWorker.getName()
                + ";"
                + firstWorker.getHired()
                + ";"
                + firstWorker.getFired()
                + ";"
                + firstWorker.getSalary()
                + ";"
                + System.lineSeparator()
                + secondWorker.getName()
                + ";"
                + secondWorker.getHired()
                + ";"
                + secondWorker.getFired()
                + ";"
                + secondWorker.getSalary()
                + ";"
                + System.lineSeparator()
                + thirdWorker.getName()
                + ";"
                + thirdWorker.getHired()
                + ";"
                + thirdWorker.getFired()
                + ";"
                + thirdWorker.getSalary()
                + ";"
                + System.lineSeparator()
                + "</body>"
                + System.lineSeparator()
                + "</html>"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenAccountReportGenerated() {
        var store = new MemStore();
        double parrotSalaryCoeff = 45;
        var now = Calendar.getInstance();
        var firstWorker = new Employee("Ivan", now, now, 100);
        var secondWorker = new Employee("Nikolay", now, now, 150);
        var thirdWorker = new Employee("Michael", now, now, 80);
        store.add(firstWorker);
        store.add(secondWorker);
        store.add(thirdWorker);
        var engine = new AccountReportEngine(store, parrotSalaryCoeff);
        var expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + firstWorker.getName()
                + ";"
                + firstWorker.getHired()
                + ";"
                + firstWorker.getFired()
                + ";"
                + firstWorker.getSalary() * parrotSalaryCoeff
                + ";"
                + System.lineSeparator()
                + secondWorker.getName()
                + ";"
                + secondWorker.getHired()
                + ";"
                + secondWorker.getFired()
                + ";"
                + secondWorker.getSalary() * parrotSalaryCoeff
                + ";"
                + System.lineSeparator()
                + thirdWorker.getName()
                + ";"
                + thirdWorker.getHired()
                + ";"
                + thirdWorker.getFired()
                + ";"
                + thirdWorker.getSalary() * parrotSalaryCoeff
                + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenReportGeneratedAndToJSON() {
        var store = new MemStore();
        var now = Calendar.getInstance();
        var worker = new Employee("Nikolay", now, now, 150);
        store.add(worker);
        JSONGenerator generator = new JSONGenerator(store);
        String expect = "[{\"name\":\""
                + worker.getName()
                + "\",\""
                + "hired\":{\"year\":"
                + now.get(Calendar.YEAR)
                + ",\"month\":"
                + now.get(Calendar.MONTH)
                + ",\"dayOfMonth\":"
                + now.get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":"
                + now.get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":"
                + now.get(Calendar.MINUTE)
                + ",\"second\":"
                + now.get(Calendar.SECOND)
                + "},\"fired\":"
                + "{\"year\":"
                + now.get(Calendar.YEAR)
                + ",\"month\":"
                + now.get(Calendar.MONTH)
                + ",\"dayOfMonth\":"
                + now.get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":"
                + now.get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":"
                + now.get(Calendar.MINUTE)
                + ",\"second\":"
                + now.get(Calendar.SECOND)
                + "},\"salary\":"
                + worker.getSalary() + "}]";
        assertThat(generator.generate(em -> true), is(expect));
    }

    @Test
    public void whenReportGeneratedAndToXML() throws DatatypeConfigurationException {
        var store = new MemStore();
        var now = Calendar.getInstance();
        var worker = new Employee("Nikolay", now, now, 150);
        store.add(worker);
        XMLGenerator generator = new XMLGenerator(store);
        XMLGregorianCalendar date = DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar((GregorianCalendar) now);
        String except = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<empList>\n"
                + "    <empList>\n"
                + "        <fired>" + date + "</fired>\n"
                + "        <hired>" + date + "</hired>\n"
                + "        <name>" + worker.getName() + "</name>\n"
                + "        <salary>" + worker.getSalary() + "</salary>\n"
                + "    </empList>\n"
                + "</empList>\n";
        assertThat(generator.generate(em -> true), is(except));
    }
}