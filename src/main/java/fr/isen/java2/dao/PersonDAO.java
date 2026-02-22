package fr.isen.java2.dao;

import fr.isen.java2.db.DatabaseManager;
import fr.isen.java2.model.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public List<Person> findAll() {
        String sql = "SELECT * FROM person";
        List<Person> persons = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                persons.add(mapResultSetToPerson(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching persons", e);
        }

        return persons;
    }

    public void deletePerson(int id) {
        String sql = "DELETE FROM person WHERE idperson = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting person with id " + id, e);
        }
    }

    public void updatePerson(Person person) {

        String sql = """
                UPDATE person
                SET lastname = ?,
                    firstname = ?,
                    nickname = ?,
                    phone_number = ?,
                    address = ?,
                    email_address = ?,
                    birth_date = ?
                WHERE idperson = ?
                """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            bindPersonParameters(ps, person);
            ps.setInt(8, person.getIdperson());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating person", e);
        }
    }

    public void createPerson(Person person) {

        String sql = """
                INSERT INTO person (
                    lastname,
                    firstname,
                    nickname,
                    phone_number,
                    address,
                    email_address,
                    birth_date
                ) VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            bindPersonParameters(ps, person);

            ps.executeUpdate();

            // Get generated ID
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setIdperson(generatedKeys.getInt(1));
                } else {
                    throw new RuntimeException("Insert failed: no ID obtained");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating person", e);
        }
    }

    private void bindPersonParameters(PreparedStatement ps, Person person) throws SQLException {

        ps.setString(1, person.getLastName());
        ps.setString(2, person.getFirstName());
        ps.setString(3, person.getNickname());
        ps.setString(4, person.getPhoneNumber());
        ps.setString(5, person.getAddress());
        ps.setString(6, person.getEmailAddress());

        if (person.getBirthDate() != null) {
            ps.setString(7, person.getBirthDate().toString());
        } else {
            ps.setNull(7, Types.VARCHAR);
        }
    }


    private Person mapResultSetToPerson(ResultSet rs) throws SQLException {
        Person p = new Person();

        p.setIdperson(rs.getInt("idperson"));
        p.setLastName(rs.getString("lastname"));
        p.setFirstName(rs.getString("firstname"));
        p.setNickname(rs.getString("nickname"));
        p.setPhoneNumber(rs.getString("phone_number"));
        p.setAddress(rs.getString("address"));
        p.setEmailAddress(rs.getString("email_address"));

        p.setBirthDate(parseNullableLocalDate(rs.getString("birth_date")));

        return p;
    }

    private LocalDate parseNullableLocalDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            return null;
        }
        return LocalDate.parse(dateStr);
    }
}