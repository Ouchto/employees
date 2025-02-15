package org.example.employeesmanager.dao;

import org.example.employeesmanager.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private Connection connection;

    public PersonDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employees", "root", "admin");


            Statement statement = connection.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS person (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "age INT NOT NULL, " +
                    "address VARCHAR(255), " +
                    "tel VARCHAR(20)" +
                    ");";

            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'person' created successfully (if it did not exist already).");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void createPerson(Person person) {
        String query = "INSERT INTO person (name, age, address, tel) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, person.getName());
            stmt.setInt(2, person.getAge());
            stmt.setString(3, person.getAddress());
            stmt.setString(4, person.getTel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<>();
        String query = "SELECT * FROM person";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
                person.setAddress(rs.getString("address"));
                person.setTel(rs.getString("tel"));
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public Person getPersonById(int id) {
        Person person = null;
        String query = "SELECT * FROM person WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    person = new Person();
                    person.setId(rs.getInt("id"));
                    person.setName(rs.getString("name"));
                    person.setAge(rs.getInt("age"));
                    person.setAddress(rs.getString("address"));
                    person.setTel(rs.getString("tel"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }


    public void updatePerson(Person person) {
        String query = "UPDATE person SET name = ?, age = ?, address = ?, tel = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, person.getName());
            stmt.setInt(2, person.getAge());
            stmt.setString(3, person.getAddress());
            stmt.setString(4, person.getTel());
            stmt.setInt(5, person.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deletePerson(int id) {
        String query = "DELETE FROM person WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
