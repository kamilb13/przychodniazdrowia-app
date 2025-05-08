package pl.projekt.przychodniazdrowia.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.przychodniazdrowia.dto.request.PatientRequest;
import pl.projekt.przychodniazdrowia.dto.response.PatientResponse;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;
import pl.projekt.przychodniazdrowia.service.PatientService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Tag(name = "Pacjent", description = "")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getPatient(@PathVariable Long id) {
        try {
            PatientResponse patientResponse = patientService.getPatient(id);
            return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<?> getAllPatients(){
        try {
            List<PatientResponse> patientResponse = patientService.getAllPatients();
            return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.status(HttpStatus.OK).body("Patient deleted with id:" + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PutMapping("/patients/{id}")
    public ResponseEntity<?> editPatient(@PathVariable Long id, @RequestBody @Valid PatientRequest patientRequest) {
        try {
            PatientResponse patientResponse = patientService.updatePatient(id, patientRequest);
            return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
