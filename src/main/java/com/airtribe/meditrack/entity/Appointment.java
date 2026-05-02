package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.abstracted.Payable;
import com.airtribe.meditrack.constants.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Appointment extends MedicalEntity implements Payable {

    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentDateTime;
    private AppointmentStatus status;
    private List<String> notes;

    public Appointment() {
        super();
        this.notes = new ArrayList<>();
        this.status = AppointmentStatus.SCHEDULED;
    }

    public Appointment(String id, Patient patient, Doctor doctor, LocalDateTime appointmentDateTime) {
        super(id);
        this.patient = patient == null ? null : patient.deepCopy();
        this.doctor = doctor == null ? null : doctor.deepCopy();
        this.appointmentDateTime = appointmentDateTime;
        this.status = AppointmentStatus.SCHEDULED;
        this.notes = new ArrayList<>();
    }

    public Appointment(Appointment other) {
        this(other.getId(), other.getPatient(), other.getDoctor(), other.getAppointmentDateTime());
        this.status = other.getStatus();
        this.notes = new ArrayList<>(other.getNotes());
        setCreatedAt(other.getCreatedAt());
    }

    public Patient getPatient() {
        return patient == null ? null : patient.deepCopy();
    }

    public void setPatient(Patient patient) {
        this.patient = patient == null ? null : patient.deepCopy();
    }

    public Doctor getDoctor() {
        return doctor == null ? null : doctor.deepCopy();
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor == null ? null : doctor.deepCopy();
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status == null ? AppointmentStatus.SCHEDULED : status;
    }

    public List<String> getNotes() {
        return new ArrayList<>(notes);
    }

    public void setNotes(List<String> notes) {
        this.notes = notes == null ? new ArrayList<>() : new ArrayList<>(notes);
    }

    public void addNote(String note) {
        if (note != null && !note.isBlank()) {
            notes.add(note);
        }
    }

    public double getBaseFee() {
        return doctor == null ? 0.0 : doctor.getConsultationFee();
    }

    @Override
    public Bill generateBill() {
        double baseAmount = getBaseFee();
        double taxAmount = baseAmount * Constants.DEFAULT_TAX_RATE;
        double totalAmount = baseAmount + taxAmount;

        return new Bill(
                "BILL-" + getId(),
                getId(),
                patient == null ? "Unknown" : patient.getName(),
                doctor == null ? "Unknown" : doctor.getName(),
                baseAmount,
                taxAmount,
                totalAmount,
                LocalDateTime.now()
        );
    }

    @Override
    public Appointment deepCopy() {
        return new Appointment(this);
    }

    @Override
    public String toString() {
        return "Appointment{id='" + getId() + "', patient=" + patient +
                ", doctor=" + doctor + ", appointmentDateTime=" + appointmentDateTime +
                ", status=" + status + ", notes=" + notes + "}";
    }
}
