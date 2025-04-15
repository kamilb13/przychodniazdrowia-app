package pl.projekt.przychodniazdrowia.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.przychodniazdrowia.dto.request.DoctorRequest;
import pl.projekt.przychodniazdrowia.dto.response.DoctorResponse;
import pl.projekt.przychodniazdrowia.service.DoctorService;

import java.util.List;

@Tag(name = "Dodanie lekarza do systemu", description = "")
@RestController
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctors")
    public ResponseEntity<?> addDoctor(@RequestBody @Valid DoctorRequest doctorRequest) {
        try {
            DoctorResponse doctorResponse = doctorService.addDoctor(doctorRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(doctorResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable Long id) {
        try {
            DoctorResponse doctorResponse = doctorService.getDoctor(id);
            return ResponseEntity.status(HttpStatus.OK).body(doctorResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/doctors")
    public ResponseEntity<?> getAllDoctors(){
        try {
            List<DoctorResponse> doctors = doctorService.getDoctors();
            return ResponseEntity.status(HttpStatus.OK).body(doctors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
