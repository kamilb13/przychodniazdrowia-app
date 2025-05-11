package pl.projekt.przychodniazdrowia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.projekt.przychodniazdrowia.dto.request.PatientRequest;
import pl.projekt.przychodniazdrowia.dto.response.PatientResponse;
import pl.projekt.przychodniazdrowia.model.Patient;
import pl.projekt.przychodniazdrowia.respository.HealthRecordRepository;
import pl.projekt.przychodniazdrowia.respository.PatientRepository;
import pl.projekt.przychodniazdrowia.service.PatientService;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private HealthRecordRepository healthRecordRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void givenPatientId_whenGetPatient_thenReturnsPatientResponse() {
        Long id = 1L;
        Patient patient = new Patient(1L ,"Anna", "Nowak", "12345678901");
        patient.setId(id);

        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        PatientResponse response = patientService.getPatient(id);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Anna", response.getName());
        assertEquals("Nowak", response.getSurname());
        assertEquals("12345678901", response.getSsn());
        verify(patientRepository).findById(id);
    }

    @Test
    void whenGetAllPatients_thenReturnsListOfPatientResponses() {
        Patient p1 = new Patient(1L,"Jan", "Kowalski", "12345678901");
        Patient p2 = new Patient(2L,"Ewa", "Nowak", "12345678910");

        when(patientRepository.findAll()).thenReturn(List.of(p1, p2));

        List<PatientResponse> responses = patientService.getAllPatients();

        assertNotNull(responses);
        assertEquals(2, responses.size());
        verify(patientRepository).findAll();
    }

    @Test
    void givenPatientId_whenDeletePatient_thenRepositoriesDeleteAreCalled() {
        Long id = 1L;

        patientService.deletePatient(id);

        verify(healthRecordRepository).deleteByPatientId(id);
        verify(patientRepository).deleteById(id);
    }

    @Test
    void givenValidIdAndRequest_whenUpdatePatient_thenReturnsUpdatedPatientResponse() {
        Long id = 1L;
        Patient existing = new Patient(1L ,"Jan", "Kowalski", "12345678901");

        PatientRequest update = new PatientRequest("Jan","Dzban","01234567890");

        when(patientRepository.findById(id)).thenReturn(Optional.of(existing));
        when(patientRepository.save(any(Patient.class))).thenReturn(existing);

        PatientResponse response = patientService.updatePatient(id, update);

        assertEquals("Jan", response.getName());
        assertEquals("Dzban", response.getSurname());
        assertEquals("01234567890", response.getSsn());
        assertEquals(id, response.getId());

        verify(patientRepository).findById(id);
        verify(patientRepository).save(existing);
    }
}
