package pl.projekt.przychodniazdrowia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.projekt.przychodniazdrowia.model.HealthRecord;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.respository.HealthRecordRepository;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {
    private final PatientRepository patientRepository;
    private final HealthRecordRepository healthRecordRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository, HealthRecordRepository healthRecordRepository) {
        this.patientRepository = patientRepository;
        this.healthRecordRepository = healthRecordRepository;
    }
    // Useless!!!!!
    // TODO dodanie pacjenta = dodanie karty pacjenta [-]
    @PostMapping("/add-patient")
    public Patient addPatient(@RequestBody Patient patient) {
        //TODO przeniesc do serwisu
        HealthRecord hr = new HealthRecord(patient, new ArrayList<>());
        healthRecordRepository.save(hr);
        return patientRepository.save(patient);
    }

    @GetMapping("/get-patients")
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
}
