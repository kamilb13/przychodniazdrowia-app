package pl.projekt.przychodniazdrowia.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.przychodniazdrowia.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
