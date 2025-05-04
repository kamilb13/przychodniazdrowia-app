package pl.projekt.przychodniazdrowia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.projekt.przychodniazdrowia.dto.request.DoctorRequest;
import pl.projekt.przychodniazdrowia.dto.response.DoctorResponse;
import pl.projekt.przychodniazdrowia.model.Doctor;
import pl.projekt.przychodniazdrowia.respository.DoctorRepository;
import pl.projekt.przychodniazdrowia.service.DoctorService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;
    
//    TODO needs fixing 
//    @Test
    void givenDoctorRequest_whenAddDoctor_thenReturnDoctorResponse() {
        DoctorRequest doctorRequest = new DoctorRequest("Jan", "Kowalski");
        Doctor doctor = new Doctor(1L, doctorRequest.getName(), doctorRequest.getSurname());

        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);
        DoctorResponse doctorResponse = doctorService.addDoctor(doctorRequest);

        assertNotNull(doctorResponse);
        assertEquals(doctorRequest.getName(), doctorResponse.getName());
        assertEquals(doctorRequest.getSurname(), doctorResponse.getSurname());
        verify(doctorRepository).save(doctor);
    }

    @Test
    void givenId_whenGetDoctorById_thenReturnDoctorResponse() {
        Long id = 1L;
        Doctor doctor = new Doctor(1L, "Jan", "Kowalski");

        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
        DoctorResponse doctorResponse = doctorService.getDoctor(id);

        assertNotNull(doctorResponse);
        assertEquals(id, doctorResponse.getId());
        assertEquals("Jan", doctorResponse.getName());
        assertEquals("Kowalski", doctorResponse.getSurname());
        verify(doctorRepository).findById(id);
    }

    @Test
    void when_getDoctors_thenReturnListOfDoctorResponse() {
        Doctor doctor1 = new Doctor(1L, "Jan", "Kowalski");
        Doctor doctor2 = new Doctor(2L, "Jan", "Kowalski");

        when(doctorRepository.findAll()).thenReturn(List.of(doctor1, doctor2));
        List<DoctorResponse> doctors = doctorService.getDoctors();

        assertNotNull(doctors);
        assertEquals(2, doctors.size());
        verify(doctorRepository).findAll();
    }
}
