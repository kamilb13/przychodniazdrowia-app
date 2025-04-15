package pl.projekt.przychodniazdrowia.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projekt.przychodniazdrowia.dto.request.DoctorRequest;
import pl.projekt.przychodniazdrowia.dto.response.DoctorResponse;
import pl.projekt.przychodniazdrowia.mapper.DoctorMapper;
import pl.projekt.przychodniazdrowia.model.Doctor;
import pl.projekt.przychodniazdrowia.respository.DoctorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorResponse addDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor(
                doctorRequest.getName(),
                doctorRequest.getSurname()
        );

        doctorRepository.save(doctor);

        return new DoctorResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getSurname()
        );
    }

    public DoctorResponse getDoctor(Long id) {
       return doctorRepository.findById(id)
               .map(DoctorMapper::mapToDto)
               .orElse(null);
    }

    public List<DoctorResponse> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(DoctorMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
