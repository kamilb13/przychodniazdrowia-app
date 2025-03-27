package pl.projekt.przychodniazdrowia.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.przychodniazdrowia.model.HealthRecord;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.model.Visit;
import pl.projekt.przychodniazdrowia.respository.HealthRecordRepository;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;

import java.util.ArrayList;
import java.util.Optional;

@Tag(name = "Rejestracja pacjenta", description = "Rejestracja pacjenta w przychodni + założenie mu karty zdrowia")
@RestController
public class PatientRegistrationController {
    private final HealthRecordRepository healthRecordRepository;
    private final PatientRepository patientRepository;

    public PatientRegistrationController(HealthRecordRepository healthRecordRepository, PatientRepository patientRepository) {
        this.healthRecordRepository = healthRecordRepository;
        this.patientRepository = patientRepository;
    }

    @PostMapping("/register-patient")
    public HealthRecord registerPatient(@RequestBody @Valid Patient patient) {
        Patient patientNew = patientRepository.save(patient);
        HealthRecord healthRecord = new HealthRecord(patientNew, new ArrayList<Visit>());
        return healthRecordRepository.save(healthRecord);
    }

    @GetMapping("/get-healthRecord/{id}")
    public ResponseEntity<HealthRecord> getHealthRecord(@PathVariable Long id) {
        return healthRecordRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
