package fr.isen.java2.dao;

import fr.isen.java2.db.DatabaseManager;
import fr.isen.java2.model.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    public List<Person> findAll() throws SQLException {
        String sql = "SELECT * FROM person";
        List<Person> persons = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Person p = new Person();
                p.setIdperson(rs.getInt("idperson"));
                p.setLastName(rs.getString("lastname"));
                p.setFirstName(rs.getString("firstname"));
                p.setNickname(rs.getString("nickname"));
                p.setPhoneNumber(rs.getString("phonenumber"));
                p.setAddress(rs.getString("address"));
                p.setEmailAddress(rs.getString("emailaddress"));
                p.setBirthDate(rs.getDate("birthdate").toLocalDate());
                persons.add(p);
            }
        }
        return persons;
    }
    
}
