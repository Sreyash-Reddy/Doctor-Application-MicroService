package com.doctorappointmentapp.doctorapplicationmicroservice.servicetests;

import com.doctorappointmentapp.doctorapplicationmicroservice.service.DoctorApplicationService;
import com.doctorappointmentapp.doctorapplicationmicroservice.service.DoctorApplicationServiceImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DoctorApplicationServiceTest {

    @Autowired
    private DoctorApplicationService doctorApplicationService;

    @Test
    void when_registerNewDoctorAccount_is_called_with_valid_name_and_availability_
}