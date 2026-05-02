package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AppointmentService {

    public interface AppointmentListener {
        void onCreated(Appointment appointment);
        void onCancelled(Appointment appointment);
    }

    private final DataStore<Appointment> appointmentStore = new DataStore<>();
    private final IdGenerator idGenerator = IdGenerator.lazyInstance();
    private final List<AppointmentListener> listeners = new java.util.ArrayList<>();

    public void addListener(AppointmentListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public void removeListener(AppointmentListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(Consumer<AppointmentListener> consumer) {
        for (AppointmentListener listener : listeners) {
            consumer.accept(listener);
        }
    }

    public Appointment createAppointment(Patient patient, Doctor doctor, LocalDateTime when) {
        String id = idGenerator.nextId("APT");
        Appointment appointment = new Appointment(id, patient, doctor, when);
        appointmentStore.save(id, appointment);
        notifyListeners(l -> l.onCreated(appointment));
        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentStore.findAll();
    }

    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentStore.findByKey(id);
    }

    public Appointment cancelAppointment(String id) {
        Appointment appointment = appointmentStore.findByKey(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found: " + id));
        appointment.setStatus(AppointmentStatus.CANCELED);
        appointmentStore.update(id, appointment);
        notifyListeners(l -> l.onCancelled(appointment));
        return appointment;
    }

    public Appointment completeAppointment(String id) {
        Appointment appointment = appointmentStore.findByKey(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found: " + id));
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentStore.update(id, appointment);
        return appointment;
    }

    public Bill generateBill(String appointmentId) {
        Appointment appointment = appointmentStore.findByKey(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found: " + appointmentId));
        return appointment.generateBill();
    }

    public Map<String, Long> appointmentsPerDoctor() {
        return appointmentStore.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        a -> a.getDoctor() == null ? "UNKNOWN" : a.getDoctor().getName(),
                        Collectors.counting()
                ));
    }

    public List<Appointment> appointmentsByStatus(AppointmentStatus status) {
        return appointmentStore.findAll()
                .stream()
                .filter(a -> a.getStatus() == status)
                .collect(Collectors.toList());
    }
}
