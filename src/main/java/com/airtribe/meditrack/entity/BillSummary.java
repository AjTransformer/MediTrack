package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;

public final class BillSummary {

    private final String billId;
    private final String appointmentId;
    private final String patientName;
    private final String doctorName;
    private final double baseAmount;
    private final double taxAmount;
    private final double totalAmount;
    private final LocalDateTime generatedAt;

    public BillSummary(String billId, String appointmentId, String patientName, String doctorName,
                       double baseAmount, double taxAmount, double totalAmount, LocalDateTime generatedAt) {
        this.billId = billId;
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.baseAmount = baseAmount;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
        this.generatedAt = generatedAt;
    }

    public String getBillId() {
        return billId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    @Override
    public String toString() {
        return "BillSummary{billId='" + billId + "', appointmentId='" + appointmentId +
                "', patientName='" + patientName + "', doctorName='" + doctorName +
                "', baseAmount=" + baseAmount + ", taxAmount=" + taxAmount +
                ", totalAmount=" + totalAmount + ", generatedAt=" + generatedAt + "}";
    }
}
