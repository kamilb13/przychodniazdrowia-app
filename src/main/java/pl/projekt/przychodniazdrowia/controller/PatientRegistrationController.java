package pl.projekt.przychodniazdrowia.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.przychodniazdrowia.dto.response.RegistrationPatientResponse;
import pl.projekt.przychodniazdrowia.model.HealthRecord;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.model.Visit;
import pl.projekt.przychodniazdrowia.respository.HealthRecordRepository;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;
import pl.projekt.przychodniazdrowia.service.PatientRegistrationService;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Rejestracja pacjenta", description = "Rejestracja pacjenta w przychodni + założenie mu karty zdrowia")
@RestController
public class PatientRegistrationController {
    private final HealthRecordRepository healthRecordRepository;
    private final PatientRepository patientRepository;
    private final PatientRegistrationService patientRegistrationService;

    public PatientRegistrationController(HealthRecordRepository healthRecordRepository, PatientRepository patientRepository, PatientRegistrationService patientRegistrationService) {
        this.healthRecordRepository = healthRecordRepository;
        this.patientRepository = patientRepository;
        this.patientRegistrationService = patientRegistrationService;
    }

    @PostMapping("/patients")
    public ResponseEntity<?> registerPatient(@RequestBody @Valid Patient patient) {
        try {
            RegistrationPatientResponse registrationPatientResponse = patientRegistrationService.registerPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(registrationPatientResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/health-records/{id}")
    public ResponseEntity<HealthRecord> getHealthRecord(@PathVariable Long id) {
        return healthRecordRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
