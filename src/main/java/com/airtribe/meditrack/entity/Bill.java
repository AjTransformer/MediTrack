package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;

public class Bill extends MedicalEntity {

    private String appointmentId;
    private String patientName;
    private String doctorName;
    private double baseAmount;
    private double taxAmount;
    private double totalAmount;
    private LocalDateTime generatedAt;

    public Bill() {
        super();
    }

    public Bill(String id, String appointmentId, String patientName, String doctorName,
                double baseAmount, double taxAmount, double totalAmount, LocalDateTime generatedAt) {
        super(id);
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.baseAmount = baseAmount;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
        this.generatedAt = generatedAt;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public BillSummary toSummary() {
        return new BillSummary(
                getId(),
                appointmentId,
                patientName,
                doctorName,
                baseAmount,
                taxAmount,
                totalAmount,
                generatedAt
        );
    }

    @Override
    public MedicalEntity deepCopy() {
        return new Bill(getId(), appointmentId, patientName, doctorName,
                baseAmount, taxAmount, totalAmount, generatedAt);
    }

    @Override
    public String toString() {
        return "Bill{id='" + getId() + "', appointmentId='" + appointmentId +
                "', patientName='" + patientName + "', doctorName='" + doctorName +
                "', baseAmount=" + baseAmount + ", taxAmount=" + taxAmount +
                ", totalAmount=" + totalAmount + ", generatedAt=" + generatedAt + "}";
    }
}
