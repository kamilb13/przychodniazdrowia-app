package pl.projekt.przychodniazdrowia.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.projekt.przychodniazdrowia.dto.request.VisitRequest;
import pl.projekt.przychodniazdrowia.dto.response.VisitResponse;
import pl.projekt.przychodniazdrowia.service.VisitService;

@RestController
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/add-visit")
    public ResponseEntity<?> addVisit(@RequestBody @Valid VisitRequest request){
        try {
            VisitResponse visitResponse = visitService.addVisit(request.getPatientId(), request.getDoctorId());
            return ResponseEntity.status(HttpStatus.CREATED).body(visitResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
