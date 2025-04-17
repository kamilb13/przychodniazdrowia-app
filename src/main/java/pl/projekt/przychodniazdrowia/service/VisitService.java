package pl.projekt.przychodniazdrowia.service;

import org.springframework.stereotype.Service;
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

    public VisitResponse addVisit(Long patientId, Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + patientId));
        HealthRecord healthRecord = healthRecordRepository.findByPatientId(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Health record not found for patient id: " + patientId));
        Visit newVisit = new Visit(healthRecord, patient, doctor, LocalDate.now()); //TODO zmienić w przyszłości date wizyty!!!
        visitRepository.save(newVisit);
        PatientResponse patientResponse = new PatientResponse(patient.getName(), patient.getSurname(), patient.getSsn());
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
}
