package pl.projekt.przychodniazdrowia.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.przychodniazdrowia.dto.request.VisitRequest;
import pl.projekt.przychodniazdrowia.dto.response.VisitResponse;
import pl.projekt.przychodniazdrowia.service.VisitService;

import java.util.List;

@Tag(name = "Wizyty", description = "")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/visits")
    public ResponseEntity<?> addVisit(@RequestBody @Valid VisitRequest visitRequest) {
        try {
            VisitResponse visitResponse = visitService.addVisit(visitRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(visitResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/visits/{id}")
    public ResponseEntity<?> getVisit(@PathVariable Long id) {
        try {
            VisitResponse visitResponse = visitService.getVisit(id);
            return ResponseEntity.status(HttpStatus.OK).body(visitResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/visits")
    public ResponseEntity<?> getAllVisits() {
        try {
            List<VisitResponse> visitResponse = visitService.getVisits();
            return ResponseEntity.status(HttpStatus.OK).body(visitResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/visits/{id}")
    public ResponseEntity<?> deleteVisit(@PathVariable Long id) {
        try {
            visitService.deleteVisit(id);
            return ResponseEntity.status(HttpStatus.OK).body("Visit deleted with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/visits/{id}")
    public ResponseEntity<?> editVisit(@PathVariable Long id, @RequestBody @Valid VisitRequest visitRequest) {
        try {
            VisitResponse visitResponse = visitService.updateVisit(id, visitRequest);
            return ResponseEntity.status(HttpStatus.OK).body(visitResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
