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
public class DoctorResponse{
    @JsonProperty("doctor_id")
    private Long id;
    @JsonProperty("doctor_name")
    private String name;
    @JsonProperty("doctor_surname")
    private String surname;

    public DoctorResponse(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
