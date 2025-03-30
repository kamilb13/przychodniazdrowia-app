package pl.projekt.przychodniazdrowia.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.projekt.przychodniazdrowia.model.Visit;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecordResponse {
    @JsonProperty("patient_id")
    private Long patientId;
    @JsonProperty("visit_list")
    private List<Visit> visitList;
}
