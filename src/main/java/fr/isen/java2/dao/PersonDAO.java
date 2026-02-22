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