package pl.projekt.przychodniazdrowia.service;

import org.springframework.stereotype.Service;
import pl.projekt.przychodniazdrowia.dto.response.HealthRecordResponse;
import pl.projekt.przychodniazdrowia.dto.response.PatientResponse;
import pl.projekt.przychodniazdrowia.model.HealthRecord;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.model.Visit;
import pl.projekt.przychodniazdrowia.respository.HealthRecordRepository;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;
import pl.projekt.przychodniazdrowia.dto.response.RegistrationPatientResponse;

import java.util.ArrayList;

@Service
public class PatientRegistrationService {
    private final PatientRepository patientRepository;
    private final HealthRecordRepository healthRecordRepository;

    public PatientRegistrationService(PatientRepository patientRepository, HealthRecordRepository healthRecordRepository) {
        this.patientRepository = patientRepository;
        this.healthRecordRepository = healthRecordRepository;
    }

    public RegistrationPatientResponse registerPatient(Patient patient) {
        Patient patientNew = patientRepository.save(patient);
        HealthRecord healthRecord = new HealthRecord(patientNew, new ArrayList<Visit>());
        healthRecordRepository.save(healthRecord);
        return new RegistrationPatientResponse(
                new PatientResponse(patientNew.getId(), patientNew.getName(), patientNew.getSurname(), patientNew.getSsn()),
                new HealthRecordResponse(healthRecord.getPatient().getId(), healthRecord.getVisitList())
        );
    }
}
