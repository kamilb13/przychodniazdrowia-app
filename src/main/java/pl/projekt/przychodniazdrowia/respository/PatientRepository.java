package pl.projekt.przychodniazdrowia.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.przychodniazdrowia.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
