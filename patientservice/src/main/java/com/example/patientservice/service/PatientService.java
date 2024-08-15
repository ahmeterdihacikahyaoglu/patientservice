package com.example.patientservice.service;

import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = getPatientById(id);
        if (existingPatient != null) {
            existingPatient.setFirstName(updatedPatient.getFirstName());
            existingPatient.setLastName(updatedPatient.getLastName());
            existingPatient.setBirthDate(updatedPatient.getBirthDate());
            existingPatient.setGender(updatedPatient.getGender());
            existingPatient.setIdentifiers(updatedPatient.getIdentifiers());
            existingPatient.setContacts(updatedPatient.getContacts());
            return patientRepository.save(existingPatient);
        }
        return null;
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findPatientsByFirstName(String firstName) {
        return patientRepository.findByFirstName(firstName);
    }

    public List<Patient> findPatientsByLastName(String lastName) {
        return patientRepository.findByLastName(lastName);
    }

    public List<Patient> findPatientsByGender(String gender) {
        return patientRepository.findByGender(gender);
    }

    public List<Patient> findPatientsByBirthDateBetween(LocalDate startDate, LocalDate endDate) {
        return patientRepository.findByBirthDateBetween(startDate, endDate);
    }

}
