package pl.projekt.przychodniazdrowia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.projekt.przychodniazdrowia.dto.response.RegistrationPatientResponse;
import pl.projekt.przychodniazdrowia.model.HealthRecord;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.respository.HealthRecordRepository;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;
import pl.projekt.przychodniazdrowia.service.PatientRegistrationService;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class PatientRegistrationServiceTest {
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private HealthRecordRepository healthRecordRepository;
    @InjectMocks
    private PatientRegistrationService patientRegistrationService;

    @Test
    void testRegisterPatient() {
        //given
        Patient patient = new Patient(1L, "Jan", "Kowalski", "123456789");
        Patient savedPatient = new Patient(1L, "Jan", "Kowalski", "123456789");
        HealthRecord healthRecord = new HealthRecord(savedPatient, new ArrayList<>());

        //when
        when(patientRepository.save(patient)).thenReturn(savedPatient);
        when(healthRecordRepository.save(any(HealthRecord.class))).thenReturn(healthRecord);
        RegistrationPatientResponse response = patientRegistrationService.registerPatient(patient);

        //then
        assertNotNull(response);
        assertEquals("Jan", response.getPatient().getName());
        assertEquals("Kowalski", response.getPatient().getSurname());
        assertEquals("123456789", response.getPatient().getSsn());
        verify(patientRepository, times(1)).save(patient);
        verify(healthRecordRepository, times(1)).save(any(HealthRecord.class));
    }
}