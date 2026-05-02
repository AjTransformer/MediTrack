package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

public class Person extends MedicalEntity {

    private String name;
    private int age;
    private String phoneNumber;

    public Person() {
        super();
    }

    public Person(String id, String name, int age, String phoneNumber) {
        super(id);
        setName(name);
        setAge(age);
        setPhoneNumber(phoneNumber);
    }

    public Person(Person other) {
        this(other.getId(), other.getName(), other.getAge(), other.getPhoneNumber());
        setCreatedAt(other.getCreatedAt());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Validator.validateName(name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = Validator.validateAge(age);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Validator.validatePhone(phoneNumber);
    }

    @Override
    public Person deepCopy() {
        return new Person(this);
    }

    @Override
    public String toString() {
        return "Person{id='" + getId() + "', name='" + name + "', age=" + age +
                ", phoneNumber='" + phoneNumber + "'}";
    }
}
