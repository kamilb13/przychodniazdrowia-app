package pl.projekt.przychodniazdrowia.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.przychodniazdrowia.model.Pacjent;

public interface PacjentRepository extends JpaRepository<Pacjent, Long> {
}
