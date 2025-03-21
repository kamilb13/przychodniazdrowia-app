package pl.projekt.przychodniazdrowia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.projekt.przychodniazdrowia.model.Doctor;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.respository.DoctorRepository;

import java.util.List;

@RestController
public class DoctorController {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    @PostMapping("/add-doctor")
    public Doctor addPatient(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @GetMapping("/get-all-doctors")
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
}
