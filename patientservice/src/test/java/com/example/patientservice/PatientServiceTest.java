package com.example.patientservice;

import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import com.example.patientservice.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patient = new Patient(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "Male",
                Arrays.asList("TCKN123456"), Arrays.asList("john.doe@example.com"), 1);
    }

    @Test
    void testGetPatientById() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));

        Patient foundPatient = patientService.getPatientById(1L);

        assertEquals("John", foundPatient.getFirstName());
        assertEquals("Doe", foundPatient.getLastName());
        assertEquals("Male", foundPatient.getGender());
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    void testCreatePatient() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient createdPatient = patientService.createPatient(patient);

        assertEquals("John", createdPatient.getFirstName());
        assertEquals("Doe", createdPatient.getLastName());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testGetAllPatients() {
        List<Patient> patients = Arrays.asList(patient);
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> allPatients = patientService.getAllPatients();

        assertEquals(1, allPatients.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testUpdatePatient() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient updatedPatient = new Patient(1L, "Jane", "Doe", LocalDate.of(1990, 1, 1), "Female",
                Arrays.asList("TCKN654321"), Arrays.asList("jane.doe@example.com"), 1);

        Patient result = patientService.updatePatient(1L, updatedPatient);

        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testDeletePatient() {
        doNothing().when(patientRepository).deleteById(anyLong());

        patientService.deletePatient(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }
}
