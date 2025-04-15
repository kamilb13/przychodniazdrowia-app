package pl.projekt.przychodniazdrowia.mapper;

import pl.projekt.przychodniazdrowia.dto.response.DoctorResponse;
import pl.projekt.przychodniazdrowia.model.Doctor;

public class DoctorMapper {
    public static DoctorResponse mapToDto(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getSurname()
        );
    }
}
