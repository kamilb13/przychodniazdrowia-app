package pl.projekt.przychodniazdrowia.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.przychodniazdrowia.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
