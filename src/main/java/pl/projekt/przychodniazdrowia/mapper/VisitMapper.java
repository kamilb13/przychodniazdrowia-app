package pl.projekt.przychodniazdrowia.mapper;

import pl.projekt.przychodniazdrowia.dto.response.VisitResponse;
import pl.projekt.przychodniazdrowia.model.Visit;

public class VisitMapper {
    public static VisitResponse mapToDto(Visit visit) {
        return new VisitResponse(
                visit.getId(),
                visit.getVisitDate(),
                visit.getPatient(),
                visit.getDoctor()
        );
    }
}
