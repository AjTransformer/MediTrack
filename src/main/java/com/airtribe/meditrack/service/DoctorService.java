package com.airtribe.meditrack.service;

import com.airtribe.meditrack.abstracted.Searchable;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorService implements Searchable {

    private final DataStore<Doctor> doctorStore = new DataStore<>();
    private final IdGenerator idGenerator = IdGenerator.lazyInstance();

    public Doctor addDoctor(Doctor doctor) {
        if (doctor.getId() == null || doctor.getId().isBlank()) {
            doctor.setId(idGenerator.nextId("DOC"));
        }
        doctorStore.save(doctor.getId(), doctor);
        return doctor;
    }

    public boolean updateDoctor(Doctor doctor) {
        return doctor != null && doctorStore.update(doctor.getId(), doctor);
    }

    public Optional<Doctor> getDoctorById(String id) {
        return doctorStore.findByKey(id);
    }

    public boolean removeDoctor(String id) {
        return doctorStore.removeByKey(id).isPresent();
    }

    public List<Doctor> getAllDoctors() {
        return doctorStore.findAll();
    }

    public List<Doctor> searchByName(String name) {
        return doctorStore.findAll()
                .stream()
                .filter(d -> containsIgnoreCase(d.getName(), name))
                .collect(Collectors.toList());
    }

    public List<Doctor> searchBySpecialization(Specialization specialization) {
        return doctorStore.findAll()
                .stream()
                .filter(d -> d.getSpecialization() == specialization)
                .collect(Collectors.toList());
    }

    public double averageFee() {
        return doctorStore.findAll()
                .stream()
                .mapToDouble(Doctor::getConsultationFee)
                .average()
                .orElse(0.0);
    }
}
