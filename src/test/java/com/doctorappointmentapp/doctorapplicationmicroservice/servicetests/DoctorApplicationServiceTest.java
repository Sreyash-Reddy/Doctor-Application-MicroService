package com.doctorappointmentapp.doctorapplicationmicroservice.servicetests;

import com.doctorappointmentapp.doctorapplicationmicroservice.entities.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.exceptions.DoctorRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.service.DoctorApplicationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DoctorApplicationServiceTest {

    @Autowired
    private DoctorApplicationService doctorApplicationService;


    //Test Cases For Doctor Registration Functionality
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_positive_Experience_return_abject(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(2).build();
        try {
            Assertions.assertNotNull(this.doctorApplicationService.registerNewDoctorAccountIntoApplication(doctor));
        } catch (DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_Null_name_notNull_specialization_notNull_positive_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name(null).specialization("Cardiologist").experience(2).build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorApplicationService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_Null_specialization_notNull_positive_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization(null).experience(2).build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorApplicationService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_Null_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(null).build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorApplicationService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_negative_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(-2).build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorApplicationService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_null_Doctor_object_throw_DoctorRegistrationException(){
        Doctor doctor = null;
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorApplicationService.registerNewDoctorAccountIntoApplication(doctor));
    }
}
