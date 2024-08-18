package com.example.patientservice;

import com.example.patientservice.controller.PatientController;
import com.example.patientservice.model.Patient;
import com.example.patientservice.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PatientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    private Patient patient;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
        patient = new Patient(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "Male",
                List.of("TCKN123456"), List.of("john.doe@example.com"), 1);

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testGetAllPatients() throws Exception {
        List<Patient> patients = List.of(patient);
        when(patientService.getAllPatients()).thenReturn(patients);

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(patients.size()))
                .andExpect(jsonPath("$[0].firstName").value(patient.getFirstName()));

        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    void testGetPatientById() throws Exception {
        when(patientService.getPatientById(anyLong())).thenReturn(patient);

        mockMvc.perform(get("/api/patients/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(patient.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(patient.getLastName()));

        verify(patientService, times(1)).getPatientById(1L);
    }

    @Test
    void testCreatePatient() throws Exception {
        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(patient.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(patient.getLastName()));

        verify(patientService, times(1)).createPatient(any(Patient.class));
    }

    @Test
    void testUpdatePatient() throws Exception {
        when(patientService.updatePatient(anyLong(), any(Patient.class))).thenReturn(patient);

        mockMvc.perform(put("/api/patients/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(patient.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(patient.getLastName()));

        verify(patientService, times(1)).updatePatient(anyLong(), any(Patient.class));
    }

    @Test
    void testDeletePatient() throws Exception {
        doNothing().when(patientService).deletePatient(anyLong());

        mockMvc.perform(delete("/api/patients/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(patientService, times(1)).deletePatient(1L);
    }
}
