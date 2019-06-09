package com.papps.freddy_lazo.domain.model;

public class User {

    private String name;
    private String lastName;
    private String age;
    private String birthDate;

    public User(String name, String lastName, String age, String birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
