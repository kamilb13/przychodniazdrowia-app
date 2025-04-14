package pl.projekt.przychodniazdrowia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "healthRecord")
    private HealthRecord healthRecord;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    LocalDate visitDate;

    public Visit(HealthRecord healthRecord, Patient patient, Doctor doctor, LocalDate visitDate) {
        this.healthRecord = healthRecord;
        this.patient = patient;
        this.doctor = doctor;
        this.visitDate = visitDate;
    }
}
