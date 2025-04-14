package pl.projekt.przychodniazdrowia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "ssn", unique = true, nullable = false)
    @Size(min = 11, max = 11, message = "SSN musi mieć dokładnie 11 znaków")
    @Pattern(regexp = "\\d{11}", message = "SSN może zawierać tylko cyfry")
    private String ssn;

}