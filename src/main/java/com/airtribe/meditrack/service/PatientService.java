package com.airtribe.meditrack.service;

import com.airtribe.meditrack.abstracted.Searchable;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientService implements Searchable {

    private final DataStore<Patient> patientStore = new DataStore<>();
    private final IdGenerator idGenerator = IdGenerator.lazyInstance();

    public Patient addPatient(Patient patient) {
        if (patient.getId() == null || patient.getId().isBlank()) {
            patient.setId(idGenerator.nextId("PAT"));
        }
        patientStore.save(patient.getId(), patient);
        return patient;
    }

    public boolean updatePatient(Patient patient) {
        return patient != null && patientStore.update(patient.getId(), patient);
    }

    public boolean removePatient(String id) {
        return patientStore.removeByKey(id).isPresent();
    }

    public Optional<Patient> getPatientById(String id) {
        return patientStore.findByKey(id);
    }

    public List<Patient> getAllPatients() {
        return patientStore.findAll();
    }

    public List<Patient> searchPatient(String query) {
        return patientStore.findAll()
                .stream()
                .filter(p -> containsIgnoreCase(p.getId(), query) || containsIgnoreCase(p.getName(), query))
                .collect(Collectors.toList());
    }

    public List<Patient> searchPatient(int age) {
        return patientStore.findAll()
                .stream()
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }
}
