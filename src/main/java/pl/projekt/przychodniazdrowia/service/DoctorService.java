package pl.projekt.przychodniazdrowia.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.projekt.przychodniazdrowia.dto.request.DoctorRequest;
import pl.projekt.przychodniazdrowia.dto.response.DoctorResponse;
import pl.projekt.przychodniazdrowia.mapper.DoctorMapper;
import pl.projekt.przychodniazdrowia.model.Doctor;
import pl.projekt.przychodniazdrowia.respository.DoctorRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

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

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public DoctorResponse updateDoctor(Long doctorID, DoctorRequest doctorRequest) {
        Doctor doctorFromDb = doctorRepository.findById(doctorID)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + doctorID));
        doctorFromDb.setName(doctorRequest.getName());
        doctorFromDb.setSurname(doctorRequest.getSurname());
        doctorRepository.save(doctorFromDb);
        return new DoctorResponse(
                doctorFromDb.getId(),
                doctorFromDb.getName(),
                doctorFromDb.getSurname()
        );
    }
}
