package com.example.patientservice.controller;

import com.example.patientservice.model.Patient;
import com.example.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(id, patient);
        if (updatedPatient != null) {
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByFirstName")
    public List<Patient> searchPatientsByFirstName(@RequestParam String firstName) {
        return patientService.findPatientsByFirstName(firstName);
    }

    @GetMapping("/searchByLastName")
    public List<Patient> searchPatientsByLastName(@RequestParam String lastName) {
        return patientService.findPatientsByLastName(lastName);
    }

    @GetMapping("/searchByGender")
    public List<Patient> searchPatientsByGender(@RequestParam String gender) {
        return patientService.findPatientsByGender(gender);
    }

    @GetMapping("/searchByAgeRange")
    public List<Patient> searchPatientsByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusYears(maxAge);
        LocalDate endDate = currentDate.minusYears(minAge);
        return patientService.findPatientsByBirthDateBetween(startDate, endDate);
    }
}
