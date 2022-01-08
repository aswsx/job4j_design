package ru.job4j.srp;

import org.junit.Test;

import java.util.Calendar;

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
}