package ru.job4j.tdd;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CinemaTest {

    @Test
    public void whenBuyTicketSuccess() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketAndDateIsWrong() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.roll(Calendar.DAY_OF_YEAR, -1);
        cinema.buy(account, 1, 1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketAndWrongPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, -1, -1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketAndSamePlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket1 = cinema.buy(account, 1, 1, date);
        Ticket ticket2 = cinema.buy(account, 1, 1, date);
    }

    @Test
    public void whenSessionExists() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Collections.singletonList(new Session3D())));
    }

    @Test
    public void whenAddSessionToListIsSuccessful() {
        Session session = new Session3D();
        List<Session> sessions = new ArrayList<>();
        sessions.add(session);
        assertThat(sessions, is(Collections.singletonList(new Session3D())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddTheSameSessionToList() {
        Session session = new Session3D();
        List<Session> sessions = new ArrayList<>();
        sessions.add(session);
        sessions.add(session);
    }
}
