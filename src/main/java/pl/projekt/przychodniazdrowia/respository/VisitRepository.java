package pl.projekt.przychodniazdrowia.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.przychodniazdrowia.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
