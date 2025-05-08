package pl.projekt.przychodniazdrowia.mapper;

import pl.projekt.przychodniazdrowia.dto.response.PatientResponse;
import pl.projekt.przychodniazdrowia.model.Patient;

public class PatientMapper {
    public static PatientResponse mapToDto(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getName(),
                patient.getSurname(),
                patient.getSsn()
        );
    }
}
