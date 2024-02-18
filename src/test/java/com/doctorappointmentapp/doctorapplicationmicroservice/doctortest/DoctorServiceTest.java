package com.doctorappointmentapp.doctorapplicationmicroservice.doctortest;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorUpdationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;
    private final Doctor testDoctor=Doctor.builder().name("Test Doctor").specialization("Neurologist").experience(3).email("testdoc@gmail.com").password("123").build();


    //Test Cases For Doctor Registration Functionality

    @Order(1)
    @DisplayName("Register Doctor Using Proper Object")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_positive_Experience_return_abject(){
        try {
            Assertions.assertNotNull(this.doctorService.registerNewDoctorAccountIntoApplication(testDoctor));
        } catch (DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Order(2)
    @DisplayName("Register Doctor Using Null")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_null_Doctor_object_throw_DoctorRegistrationException(){
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(null));
    }

    @Order(3)
    @DisplayName("Register Doctor Using Null Name")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_Null_name_notNull_specialization_notNull_positive_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name(null).specialization("Cardiologist").experience(2).email("admin2@gmail.com").password("123").build();

        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Order(4)
    @DisplayName("Register Doctor Using Null Specialization")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_Null_specialization_notNull_positive_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization(null).experience(2).email("admin2@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Order(5)
    @DisplayName("Register Doctor Using Null Experience")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_Null_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(null).email("admin2@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Order(6)
    @DisplayName("Register Doctor Using Negative Experience")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_negative_Experience_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(-2).email("admin2@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @Order(7)
    @DisplayName("Register Doctor Using Null Email")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_Null_email_notNull_password_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(2).email(null).password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }
    @Order(8)
    @DisplayName("Register Doctor Using Existing Email")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_existing_email_notNull_password_throw_DoctorRegistrationException(){
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(testDoctor));
    }

    @Order(9)
    @DisplayName("Register Doctor Using Null Password")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_email_Null_password_throw_DoctorRegistrationException(){
        Doctor doctor = Doctor.builder().name("Qwerty").specialization("Cardiologist").experience(2).email("admin2@gmail.com").password(null).build();
        Assertions.assertThrows(DoctorRegistrationException.class,()->this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }


    //doctorLogin
    @Order(10)
    @DisplayName("Doctor Login Using Correct Email And Password")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_notNull_email_notNull_password_return_doctor_object() {
        try {
            Assertions.assertNotNull(this.doctorService.loginDoctorAccountIntoApplication(testDoctor.getEmail(), testDoctor.getPassword()));
        } catch (DoctorLoginException e) {
            throw new RuntimeException(e);
        }
    }

    @Order(11)
    @DisplayName("Doctor Login Using Null Email")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_Null_email_notNull_password_throw_DoctorLoginException() {
        Assertions.assertThrows(DoctorLoginException.class, ()->this.doctorService.loginDoctorAccountIntoApplication(null, "string"));
    }

    @Order(12)
    @DisplayName("Doctor Login Using Null Password")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_notNull_email_Null_password_throw_DoctorLoginException() {
        Assertions.assertThrows(DoctorLoginException.class, ()->this.doctorService.loginDoctorAccountIntoApplication("qwerty@gmail.com", null));
    }

    @Order(13)
    @DisplayName("Doctor Login Using Non-Existing Email")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_notNull_nonExisting_email_notNull_password_throw_DoctorLoginException() {
        Assertions.assertThrows(DoctorLoginException.class, ()->this.doctorService.loginDoctorAccountIntoApplication("abc@gmail.com", "string"));
    }

    @Order(14)
    @DisplayName("Doctor Login Using Incorrect Password")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_notNull_Existing_email_notNull_incorrect_password_throw_DoctorLoginException() {
        Assertions.assertThrows(DoctorLoginException.class, ()->this.doctorService.loginDoctorAccountIntoApplication("qwerty@gmail.com", "password"));
    }


    //Tests for updating doctor details functionality
    @Order(15)
    @DisplayName("Updating Doctor Giving All Details")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_positive_Experience_return_object(){
        Doctor updatedDoctor= Doctor.builder().name("Updated Test Doctor").specialization("Pulmonologist").experience(3).email("testdoc@gmail.com").password("123").build();
        try {
            Assertions.assertNotNull(this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
//            Assertions.assertEquals(this.doctorService.loginDoctorAccountIntoApplication(updatedDoctor.getEmail(), updatedDoctor.getPassword()),updatedDoctor);
        } catch (DoctorUpdationException e) {
            throw new RuntimeException(e);
        }
    }

    @Order(16)
    @DisplayName("Updating Doctor Using Non-Existing Email")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_nonExisting_email_throw_DoctorUpdationException(){
        Doctor updatedDoctor= Doctor.builder().name("Updated Test Doctor").specialization("Pulmonologist").experience(3).email("nonExisting@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class,()->this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }

    @Order(17)
    @DisplayName("Updating Doctor Using Null Object")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_null_doctor_object_throw_DoctorUpdationException(){
       Assertions.assertThrows(DoctorUpdationException.class,()->this.doctorService.updateDoctorAccountIntoApplication(null));
    }

    @Order(18)
    @DisplayName("Updating Doctor Using Null Name")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_Null_name_notNull_specialization_notNull_positive_Experience_throw_DoctorUpdationException(){
        Doctor updatedDoctor= Doctor.builder().name(null).specialization("Pulmonologist").experience(3).email("testdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class,()->this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }

    @Order(19)
    @DisplayName("Updating Doctor Using Null Specialization")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_name_Null_specialization_notNull_positive_Experience_throw_DoctorUpdationException(){
        Doctor updatedDoctor= Doctor.builder().name("Test Doctor").specialization(null).experience(3).email("testdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class,()->this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }

    @Order(20)
    @DisplayName("Updating Doctor Using Null Experience")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_Null_positive_Experience_throw_DoctorUpdateException(){
        Doctor updatedDoctor= Doctor.builder().name("Test Doctor").specialization("Pulmonologist").experience(null).email("testdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class,()->this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }

    @Order(21)
    @DisplayName("Updating Doctor Using Negative Experience")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_negative_Experience_throw_DoctorUpdateException(){
        Doctor updatedDoctor= Doctor.builder().name("Test Doctor").specialization("Pulmonologist").experience(-2000).email("testdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class,()->this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }

//    Testcases for delete doctor functionality
    @Order(22)
    @DisplayName("Deleting Object Using Existing Email")
    @Test
    void when_deleteDoctorAccountFromApplication_is_called_with_notNull_email_notNull_password_return_deleted_object(){
        try {
            Doctor deletedDoctor=this.doctorService.deleteDoctorAccountFromApplication(testDoctor.getEmail(), testDoctor.getPassword());
            Assertions.assertNotNull(deletedDoctor);
        } catch (DoctorDeletionException  e) {
            throw new RuntimeException(e);
        }
    }

    @Order(23)
    @DisplayName("Deleting Object Using Null Email")
    @Test
    void when_deleteDoctorAccountFromApplication_is_called_with_null_email_notNull_password_return_DoctorDeletionException(){
       Assertions.assertThrows(DoctorDeletionException.class,()->this.doctorService.deleteDoctorAccountFromApplication(null, testDoctor.getPassword()));
    }

    @Order(24)
    @DisplayName("Deleting Object Using Null Password")
    @Test
    void when_deleteDoctorAccountFromApplication_is_called_with_notNull_email_null_password_return_DoctorDeletionException(){
        Assertions.assertThrows(DoctorDeletionException.class,()->this.doctorService.deleteDoctorAccountFromApplication(testDoctor.getEmail(), null));
    }

    @Order(25)
    @DisplayName("Deleting Object Using Non-Existing Email")
    @Test
    void when_deleteDoctorAccountFromApplication_is_called_with_notNull_nonExisting_email_notNull_password_return_DoctorDeletionException(){
        Assertions.assertThrows(DoctorDeletionException.class,()->this.doctorService.deleteDoctorAccountFromApplication("nonExisting@gmail.com", testDoctor.getPassword()));
    }

}