package com.doctorappointmentapp.doctorapplicationmicroservice.servicetests;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;


    //Test Cases For Doctor Registration Functionality
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_positive_Experience_return_abject(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(2).email("admin1@gmail.com").password("123").build();
        try {
            Assertions.assertNotNull(this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
        } catch (DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_null_Doctor_object_throw_DoctorRegistrationException(){
        Doctor doctor = null;
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_Null_name_notNull_specialization_notNull_positive_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name(null).specialization("Cardiologist").experience(2).email("admin2@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_Null_specialization_notNull_positive_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization(null).experience(2).email("admin2@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_Null_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(null).email("admin2@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_negative_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(-2).email("admin2@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_Null_email_notNull_password_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(2).email(null).password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_existing_email_notNull_password_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(2).email("admin1@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }


    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_email_Null_password_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(2).email("admin2@gmail.com").password(null).build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }



}
