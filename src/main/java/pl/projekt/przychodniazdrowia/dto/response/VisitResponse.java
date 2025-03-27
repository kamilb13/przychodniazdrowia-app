package pl.projekt.przychodniazdrowia.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitResponse {
    @JsonProperty("visit_id")
    private Long visitId;
    @JsonProperty("visit_date")
    private LocalDate visitDate;
    @JsonProperty("patient")
    PatientResponse patient;
    @JsonProperty("doctor")
    DoctorResponse doctor;
}
