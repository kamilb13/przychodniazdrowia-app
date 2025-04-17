package pl.projekt.przychodniazdrowia.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.przychodniazdrowia.dto.response.PatientResponse;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;
import pl.projekt.przychodniazdrowia.service.PatientService;

import java.util.List;

@Hidden
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getPatient(Long id) {
        try {
            PatientResponse patientResponse = patientService.getPatient(id);
            return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<?> getAllPatients(){
        try {
            List<PatientResponse> patientResponse = patientService.getAllPatients();
            return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
