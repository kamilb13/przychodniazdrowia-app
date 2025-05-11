package pl.projekt.przychodniazdrowia;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.projekt.przychodniazdrowia.dto.request.VisitRequest;
import pl.projekt.przychodniazdrowia.dto.response.VisitResponse;
import pl.projekt.przychodniazdrowia.mapper.VisitMapper;
import pl.projekt.przychodniazdrowia.model.*;
import pl.projekt.przychodniazdrowia.respository.*;
import pl.projekt.przychodniazdrowia.service.VisitService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitServiceTest {

    @Mock VisitRepository visitRepository;

    @Mock PatientRepository patientRepository;

    @Mock DoctorRepository doctorRepository;

    @Mock HealthRecordRepository healthRecordRepository;

    @InjectMocks VisitService visitService;
}
