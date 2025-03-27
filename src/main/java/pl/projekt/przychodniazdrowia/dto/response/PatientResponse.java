package pl.projekt.przychodniazdrowia.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse{
    @JsonProperty("patient_name")
    private String patientName;
    @JsonProperty("patient_surname")
    private String patientSurname;
    @JsonProperty("patient_ssn")
    private String patientSsn;
}
