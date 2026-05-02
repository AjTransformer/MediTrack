package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();

        appointmentService.addListener(new AppointmentService.AppointmentListener() {
            @Override
            public void onCreated(Appointment appointment) {
                System.out.println("[Notification] Appointment created: " + appointment.getId());
            }

            @Override
            public void onCancelled(Appointment appointment) {
                System.out.println("[Notification] Appointment cancelled: " + appointment.getId());
            }
        });

        boolean running = true;
        while (running) {
            System.out.println("\n=== MediTrack ===");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Create Appointment");
            System.out.println("4. View Doctors");
            System.out.println("5. View Patients");
            System.out.println("6. View Appointments");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. Generate Bill");
            System.out.println("9. Doctor Analytics");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Doctor name: ");
                    String name = scanner.nextLine();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Specialization (GENERAL/CARDIOLOGY/DERMATOLOGY/ORTHOPEDICS/PEDIATRICS/NEUROLOGY): ");
                    Specialization spec = Specialization.valueOf(scanner.nextLine().trim().toUpperCase());
                    System.out.print("Fee: ");
                    double fee = Double.parseDouble(scanner.nextLine());

                    Doctor doctor = new Doctor(null, name, age, phone, spec, fee);
                    doctorService.addDoctor(doctor);
                    System.out.println("Added: " + doctor);
                }
                case "2" -> {
                    System.out.print("Patient name: ");
                    String name = scanner.nextLine();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Gender: ");
                    String gender = scanner.nextLine();

                    Patient patient = new Patient(null, name, age, phone, gender);
                    patientService.addPatient(patient);
                    System.out.println("Added: " + patient);
                }
                case "3" -> {
                    System.out.print("Patient ID: ");
                    String patientId = scanner.nextLine();
                    System.out.print("Doctor ID: ");
                    String doctorId = scanner.nextLine();
                    System.out.print("Date-time (yyyy-MM-ddTHH:mm): ");
                    LocalDateTime when = LocalDateTime.parse(scanner.nextLine());

                    Patient patient = patientService.getPatientById(patientId).orElseThrow();
                    Doctor doctor = doctorService.getDoctorById(doctorId).orElseThrow();
                    Appointment appointment = appointmentService.createAppointment(patient, doctor, when);
                    System.out.println("Created: " + appointment);
                }
                case "4" -> doctorService.getAllDoctors().forEach(System.out::println);
                case "5" -> patientService.getAllPatients().forEach(System.out::println);
                case "6" -> appointmentService.getAllAppointments().forEach(System.out::println);
                case "7" -> {
                    System.out.print("Appointment ID: ");
                    String id = scanner.nextLine();
                    System.out.println("Updated: " + appointmentService.cancelAppointment(id));
                }
                case "8" -> {
                    System.out.print("Appointment ID: ");
                    String id = scanner.nextLine();
                    System.out.println("Bill: " + appointmentService.generateBill(id));
                }
                case "9" -> {
                    System.out.println("Average doctor fee: " + doctorService.averageFee());
                    System.out.println("Appointments per doctor: " + appointmentService.appointmentsPerDoctor());
                }
                case "0" -> running = false;
                default -> System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }
}