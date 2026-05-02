package com.airtribe.meditrack.entity;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

    private String gender;
    private List<String> allergies;

    public Patient() {
        super();
        this.allergies = new ArrayList<>();
    }

    public Patient(String id, String name, int age, String phoneNumber, String gender) {
        super(id, name, age, phoneNumber);
        this.gender = gender;
        this.allergies = new ArrayList<>();
    }

    public Patient(Patient other) {
        this(other.getId(), other.getName(), other.getAge(), other.getPhoneNumber(), other.getGender());
        this.allergies = new ArrayList<>(other.getAllergies());
        setCreatedAt(other.getCreatedAt());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getAllergies() {
        return new ArrayList<>(allergies);
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies == null ? new ArrayList<>() : new ArrayList<>(allergies);
    }

    public void addAllergy(String allergy) {
        if (allergy != null && !allergy.isBlank()) {
            this.allergies.add(allergy);
        }
    }

    @Override
    public Patient deepCopy() {
        return new Patient(this);
    }

    @Override
    public String toString() {
        return "Patient{id='" + getId() + "', name='" + getName() + "', age=" + getAge() +
                ", phoneNumber='" + getPhoneNumber() + "', gender='" + gender +
                "', allergies=" + allergies + "}";
    }
}
