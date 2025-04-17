package pl.projekt.przychodniazdrowia.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.projekt.przychodniazdrowia.mapper.DoctorMapper;
import pl.projekt.przychodniazdrowia.mapper.PatientMapper;
import pl.projekt.przychodniazdrowia.model.Doctor;
import pl.projekt.przychodniazdrowia.model.Patient;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("patient")
    PatientResponse patient;
    @JsonProperty("doctor")
    DoctorResponse doctor;


    public VisitResponse(Long id, LocalDate visitDate, Patient patient, Doctor doctor) {
        this.id = id;
        this.date = visitDate;
        this.patient = PatientMapper.mapToDto(patient);
        this.doctor = DoctorMapper.mapToDto(doctor);
    }
}
