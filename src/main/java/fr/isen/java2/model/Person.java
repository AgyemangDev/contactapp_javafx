package fr.isen.java2.model;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class Person {
    private int idperson;
    private String lastName;
    private String firstName;
    private String nickname;
    private String phoneNumber;
    private String address;
    private String emailAddress;
    private LocalDate birthDate;
    private Image photo;

    //Constructor
    public Person() {
    }

    // Getters

    public int getIdperson() {
        return idperson;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Image getPhoto() {
        return photo;
    }

    // Setters

    public void setIdperson(int idperson) {
        this.idperson = idperson;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }
}
