package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import java.time.LocalDateTime;

public class TestRunner {

    public static void main(String[] args) {
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();

        Doctor doctor = new Doctor(null, "Dr Smith", 42, "9999999999",
                Specialization.CARDIOLOGY, 500.0);
        Patient patient = new Patient(null, "John Doe", 30, "8888888888", "Male");

        doctorService.addDoctor(doctor);
        patientService.addPatient(patient);

        var appointment = appointmentService.createAppointment(
                patient,
                doctor,
                LocalDateTime.now().plusDays(1)
        );

        System.out.println("Doctor count = " + doctorService.getAllDoctors().size());
        System.out.println("Patient count = " + patientService.getAllPatients().size());
        System.out.println("Appointment = " + appointment);
        System.out.println("Bill = " + appointmentService.generateBill(appointment.getId()));
    }
}
