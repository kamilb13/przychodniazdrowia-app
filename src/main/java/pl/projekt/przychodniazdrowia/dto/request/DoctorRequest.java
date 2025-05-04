package pl.projekt.przychodniazdrowia.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DoctorRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
}
