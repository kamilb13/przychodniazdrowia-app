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
    private String name;
    @JsonProperty("patient_surname")
    private String surname;
    @JsonProperty("patient_ssn")
    private String ssn;
}
