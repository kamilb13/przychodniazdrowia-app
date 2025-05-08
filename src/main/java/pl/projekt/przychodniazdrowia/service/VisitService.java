package pl.projekt.przychodniazdrowia.service;

import org.springframework.stereotype.Service;
import pl.projekt.przychodniazdrowia.dto.request.VisitRequest;
import pl.projekt.przychodniazdrowia.dto.response.DoctorResponse;
import pl.projekt.przychodniazdrowia.dto.response.PatientResponse;
import pl.projekt.przychodniazdrowia.dto.response.VisitResponse;
import pl.projekt.przychodniazdrowia.mapper.VisitMapper;
import pl.projekt.przychodniazdrowia.model.Doctor;
import pl.projekt.przychodniazdrowia.model.HealthRecord;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.model.Visit;
import pl.projekt.przychodniazdrowia.respository.DoctorRepository;
import pl.projekt.przychodniazdrowia.respository.HealthRecordRepository;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;
import pl.projekt.przychodniazdrowia.respository.VisitRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final HealthRecordRepository healthRecordRepository;

    public VisitService(VisitRepository visitRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, HealthRecordRepository healthRecordRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.healthRecordRepository = healthRecordRepository;
    }

    public VisitResponse addVisit(VisitRequest visitRequest) {
        Doctor doctor = doctorRepository.findById(visitRequest.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + visitRequest.getDoctorId()));
        Patient patient = patientRepository.findById(visitRequest.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + visitRequest.getPatientId()));
        HealthRecord healthRecord = healthRecordRepository.findByPatientId(visitRequest.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Health record not found for patient id: " + visitRequest.getPatientId()));
        Visit newVisit = new Visit(healthRecord, patient, doctor, LocalDate.now()); //TODO zmienić w przyszłości date wizyty!!!
        visitRepository.save(newVisit);
        PatientResponse patientResponse = new PatientResponse(patient.getId(), patient.getName(), patient.getSurname(), patient.getSsn());
        DoctorResponse doctorResponse = new DoctorResponse(doctor.getName(), doctor.getSurname());
        return new VisitResponse(newVisit.getId(), newVisit.getVisitDate(), patientResponse, doctorResponse);
    }
    
    public VisitResponse getVisit(Long id) {
        return visitRepository.findById(id)
                .map(VisitMapper::mapToDto)
                .orElse(null);
    }

    public List<VisitResponse> getVisits() {
        List<Visit> visits = visitRepository.findAll();
        return visits.stream()
                .map(VisitMapper::mapToDto)
                .collect(Collectors.toList());
    }
    
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
    
    // TODO needs fixing 
    public VisitResponse updateVisit(Long id, VisitRequest visitRequest) {
        Visit visitFromDb = visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + id));
        Patient patientFromDb = patientRepository.findById(visitFromDb.getPatient().getId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: [" + visitFromDb.getPatient().getId()));
        HealthRecord healthRecordFromDb = healthRecordRepository.findByPatientId(patientFromDb.getId())
                .orElseThrow(() -> new IllegalArgumentException("Health record not found for patient id: " + patientFromDb.getId()));
        
        visitFromDb.setPatient(patientFromDb);
        visitFromDb.setHealthRecord(healthRecordFromDb);
        visitFromDb.setVisitDate(visitRequest.getDate());
        visitRepository.save(visitFromDb);
        return new VisitResponse(
            visitFromDb.getId(),
            visitFromDb.getVisitDate(),
            visitFromDb.getPatient(),
            visitFromDb.getDoctor()    
        );
    }
}
