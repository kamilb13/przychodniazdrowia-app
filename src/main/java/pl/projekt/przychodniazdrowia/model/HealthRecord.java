package pl.projekt.przychodniazdrowia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "health-records")
public class HealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name="patient_id", unique = true, nullable = false)
    private Patient patient;

    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visit> visitList;

    public HealthRecord(Patient patient, List<Visit> visitList) {
        this.patient = patient;
        this.visitList = visitList;
    }
}
