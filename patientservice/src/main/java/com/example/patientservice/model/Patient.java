package com.example.patientservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;

    @ElementCollection
    private List<String> identifiers; // TCKN, Pasaport verilerini tutmak için

    @ElementCollection
    private List<String> contacts; // Telefon numarası, e-posta verilerini tutmak için

    @Version
    private Integer version;
}
