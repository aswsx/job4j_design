package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatementDemo {
    private Connection connection;

    public PrepareStatementDemo() throws ClassNotFoundException, SQLException {
        initConnection();
    }

    public void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        var url = "jdbc:postgresql://localhost:5432/idea_db";
        var login = "postgres";
        var password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement("insert into cities(name, population) values (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public boolean update(City city) {
        var result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        var result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        var psd = new PrepareStatementDemo();
        var city = new City(1, "Nur-Sultan", 1200000);
        var anotherCity = new City(2, "Tokyo", 13960000);
        psd.insert(city);
        psd.insert(anotherCity);
        anotherCity.setPopulation(14000000);
        psd.update(anotherCity);
        var allCities = psd.findAll();
        for (var city1 : allCities) {
            System.out.println(city1.toString());
        }
        System.out.println(anotherCity.getId());
        System.out.println(psd.delete(anotherCity.getId()));
        allCities = psd.findAll();
        for (var city2 : allCities) {
            System.out.println(city2.toString());
        }
    }
}