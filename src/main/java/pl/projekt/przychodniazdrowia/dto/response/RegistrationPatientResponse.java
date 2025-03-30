package pl.projekt.przychodniazdrowia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationPatientResponse {
    private PatientResponse patient;
    private HealthRecordResponse healthRecordResponse;
}