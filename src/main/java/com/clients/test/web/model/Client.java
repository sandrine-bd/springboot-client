package com.clients.test.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Client {
    private int id;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    @Size(min = 9, max = 9)
    private String licenseNumber;

    public Client() {}

    public Client(int id, String lastName, String firstName, LocalDate birthDate, String licenseNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "Client {" +
                "id = " + id +
                ", nom = " + lastName +
                ", prénom = " + firstName +
                ", date de naissance = " + birthDate +
                ", numéro de permis = " + licenseNumber +
                '}';
    }

    /**
     * Getters et setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
