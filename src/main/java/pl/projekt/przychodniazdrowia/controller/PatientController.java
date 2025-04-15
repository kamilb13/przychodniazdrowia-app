package pl.projekt.przychodniazdrowia.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.projekt.przychodniazdrowia.model.HealthRecord;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.respository.HealthRecordRepository;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;

import java.util.ArrayList;
import java.util.List;

@Hidden
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientController {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
}
