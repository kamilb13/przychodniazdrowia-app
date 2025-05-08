package pl.projekt.przychodniazdrowia.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.projekt.przychodniazdrowia.dto.request.PatientRequest;
import pl.projekt.przychodniazdrowia.dto.response.PatientResponse;
import pl.projekt.przychodniazdrowia.mapper.PatientMapper;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.respository.HealthRecordRepository;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final HealthRecordRepository healthRecordRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, HealthRecordRepository healthRecordRepository) {
        this.patientRepository = patientRepository;
        this.healthRecordRepository = healthRecordRepository;
    }

    public PatientResponse getPatient(Long id) {
        return patientRepository.findById(id)
                .map(PatientMapper::mapToDto)
                .orElse(null);
    }

    public List<PatientResponse> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePatient(Long id) {
        healthRecordRepository.deleteByPatientId(id);
        patientRepository.deleteById(id);
    }

    public PatientResponse updatePatient(Long id, PatientRequest patientRequest) {
        Patient patientFromDb = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient with id " + id + " not found"));
        patientFromDb.setName(patientRequest.getName());
        patientFromDb.setSurname(patientRequest.getSurname());
        patientFromDb.setSsn(patientRequest.getSsn());
        patientRepository.save(patientFromDb);
        return new PatientResponse (
                patientFromDb.getId(),
                patientFromDb.getName(),
                patientFromDb.getSurname(),
                patientFromDb.getSsn()
        );
    }
}
