package pl.projekt.przychodniazdrowia.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitRequest {
    @NotNull
    @Positive
    @JsonProperty("patient_id")
    private Long patientId;
    @NotNull
    @Positive
    @JsonProperty("doctor_id")
    private Long doctorId;
}
