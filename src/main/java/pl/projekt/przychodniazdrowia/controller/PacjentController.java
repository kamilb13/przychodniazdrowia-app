package pl.projekt.przychodniazdrowia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.projekt.przychodniazdrowia.model.Pacjent;
import pl.projekt.przychodniazdrowia.respository.PacjentRepository;

import java.util.List;

@RestController
public class PacjentController {
    private final PacjentRepository pacjentRepository;

    @Autowired
    public PacjentController(PacjentRepository pacjentRepository) {
        this.pacjentRepository = pacjentRepository;
    }

    @PostMapping("/add-pacjent")
    public Pacjent addPacjent(@RequestBody Pacjent pacjent) {
        return pacjentRepository.save(pacjent);
    }

    @GetMapping("/get-pacjenci")
    public List<Pacjent> getPacjenci(){
        return pacjentRepository.findAll();
    }
}
