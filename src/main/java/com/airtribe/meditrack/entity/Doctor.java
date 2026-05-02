package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

public class Doctor extends Person {

    private Specialization specialization;
    private double consultationFee;

    public Doctor() {
        super();
    }

    public Doctor(String id, String name, int age, String phoneNumber,
                  Specialization specialization, double consultationFee) {
        super(id, name, age, phoneNumber);
        setSpecialization(specialization);
        setConsultationFee(consultationFee);
    }

    public Doctor(Doctor other) {
        this(other.getId(), other.getName(), other.getAge(), other.getPhoneNumber(),
                other.getSpecialization(), other.getConsultationFee());
        setCreatedAt(other.getCreatedAt());
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization == null ? Specialization.GENERAL : specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = Validator.validateFee(consultationFee);
    }

    @Override
    public Doctor deepCopy() {
        return new Doctor(this);
    }

    @Override
    public String toString() {
        return "Doctor{id='" + getId() + "', name='" + getName() + "', age=" + getAge() +
                ", phoneNumber='" + getPhoneNumber() + "', specialization=" + specialization +
                ", consultationFee=" + consultationFee + "}";
    }
}
