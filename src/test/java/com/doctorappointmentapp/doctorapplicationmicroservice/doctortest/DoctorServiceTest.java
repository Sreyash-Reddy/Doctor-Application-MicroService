package com.doctorappointmentapp.doctorapplicationmicroservice.doctortest;

import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientAppointmentsFetchingException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.*;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;
    private Doctor internalTestDoctor = Doctor.builder().name("Internal Test Doctor").specialization("Neurologist").experience(3).email("internalTestdoc@gmail.com").password("123").build();
    private Doctor externalTestDoctor = Doctor.builder().name("External Test Doctor").specialization("Neurologist").experience(3).email("externalTestdoc@gmail.com").password("123").build();


    @BeforeEach
    void beforeEachTest() {
        this.doctorService.deleteAllDoctors();
        try {
            this.doctorService.registerNewDoctorAccountIntoApplication(internalTestDoctor);
        } catch (DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    //Test Cases For Doctor Registration Functionality


    @DisplayName("Register Doctor Using Proper Object")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_positive_Experience_return_abject() {
        try {
            Assertions.assertNotNull(this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
        } catch (DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("Register Doctor Using Null")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_null_Doctor_object_throw_DoctorRegistrationException() {
        Assertions.assertThrows(DoctorRegistrationException.class, () -> this.doctorService.registerNewDoctorAccountIntoApplication(null));
    }


    @DisplayName("Register Doctor Using Null Name")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_Null_name_notNull_specialization_notNull_positive_Experience_throw_DoctorRegistrationException() {
        Doctor doctor = Doctor.builder().name(null).specialization("Neurologist").experience(3).email("internalTestdoc@gmail.com").password("123").build();

        Assertions.assertThrows(DoctorRegistrationException.class, () -> this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }


    @DisplayName("Register Doctor Using Null Specialization")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_Null_specialization_notNull_positive_Experience_throw_DoctorRegistrationException() {
        Doctor doctor = Doctor.builder().name("Internal Test Doctor").specialization(null).experience(3).email("internalTestdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class, () -> this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }


    @DisplayName("Register Doctor Using Null Experience")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_Null_Experience_throw_DoctorRegistrationException() {
        Doctor doctor = Doctor.builder().name("Internal Test Doctor").specialization("Neurologist").experience(null).email("internalTestdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class, () -> this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }


    @DisplayName("Register Doctor Using Negative Experience")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_negative_Experience_throw_DoctorRegistrationException() {
        Doctor doctor = Doctor.builder().name("Internal Test Doctor").specialization("Neurologist").experience(-3).email("internalTestdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class, () -> this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }


    @DisplayName("Register Doctor Using Null Email")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_Null_email_notNull_password_throw_DoctorRegistrationException() {
        Doctor doctor = Doctor.builder().name("Internal Test Doctor").specialization("Neurologist").experience(3).email(null).password("123").build();
        Assertions.assertThrows(DoctorRegistrationException.class, () -> this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }

    @DisplayName("Register Doctor Using Existing Email")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_existing_email_notNull_password_throw_DoctorRegistrationException() {
        Assertions.assertThrows(DoctorRegistrationException.class, () -> this.doctorService.registerNewDoctorAccountIntoApplication(internalTestDoctor));
    }


    @DisplayName("Register Doctor Using Null Password")
    @Test
    void when_registerNewDoctorAccountIntoApplication_is_called_with_notNull_email_Null_password_throw_DoctorRegistrationException() {
        Doctor doctor = Doctor.builder().name("Internal Test Doctor").specialization("Neurologist").experience(3).email("internalTestdoc@gmail.com").password(null).build();
        Assertions.assertThrows(DoctorRegistrationException.class, () -> this.doctorService.registerNewDoctorAccountIntoApplication(doctor));
    }


    //Tests for Doctor Login Functionality

    @DisplayName("Doctor Login Using Correct Email And Password")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_notNull_email_notNull_password_return_doctor_object() {
        try {
            Assertions.assertNotNull(this.doctorService.loginDoctorAccountIntoApplication(internalTestDoctor.getEmail(), internalTestDoctor.getPassword()));
        } catch (DoctorLoginException e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("Doctor Login Using Null Email")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_Null_email_notNull_password_throw_DoctorLoginException() {
        Assertions.assertThrows(DoctorLoginException.class, () -> this.doctorService.loginDoctorAccountIntoApplication(null, "123"));
    }


    @DisplayName("Doctor Login Using Null Password")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_notNull_email_Null_password_throw_DoctorLoginException() {
        Assertions.assertThrows(DoctorLoginException.class, () -> this.doctorService.loginDoctorAccountIntoApplication(internalTestDoctor.getEmail(), null));
    }


    @DisplayName("Doctor Login Using Non-Existing Email")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_notNull_nonExisting_email_notNull_password_throw_DoctorLoginException() {
        Assertions.assertThrows(DoctorLoginException.class, () -> this.doctorService.loginDoctorAccountIntoApplication("nonExisting@gmail.com", "123"));
    }


    @DisplayName("Doctor Login Using Incorrect Password")
    @Test
    void when_loginNewDoctorAccountIntoApplication_is_called_with_notNull_Existing_email_notNull_incorrect_password_throw_DoctorLoginException() {
        Assertions.assertThrows(DoctorLoginException.class, () -> this.doctorService.loginDoctorAccountIntoApplication(internalTestDoctor.getEmail(), "incorrectPassword"));
    }


    //Tests for updating doctor details functionality

    @DisplayName("Updating Doctor Giving All Details")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_positive_Experience_return_object() {
        Doctor updatedDoctor = Doctor.builder().name("Internal Test Doctor Updated").specialization("Neurologist").experience(3).email("internalTestdoc@gmail.com").password("123").build();
        try {
            Assertions.assertNotNull(this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
        } catch (DoctorUpdationException e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("Updating Doctor Using Non-Existing Email")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_nonExisting_email_throw_DoctorUpdationException() {
        Doctor updatedDoctor = Doctor.builder().name("Updated Test Doctor").specialization("Pulmonologist").experience(3).email("nonExisting@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class, () -> this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }


    @DisplayName("Updating Doctor Using Null Object")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_null_doctor_object_throw_DoctorUpdationException() {
        Assertions.assertThrows(DoctorUpdationException.class, () -> this.doctorService.updateDoctorAccountIntoApplication(null));
    }


    @DisplayName("Updating Doctor Using Null Name")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_Null_name_notNull_specialization_notNull_positive_Experience_throw_DoctorUpdationException() {
        Doctor updatedDoctor = Doctor.builder().name(null).specialization("Neurologist").experience(3).email("internalTestdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class, () -> this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }


    @DisplayName("Updating Doctor Using Null Specialization")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_name_Null_specialization_notNull_positive_Experience_throw_DoctorUpdationException() {
        Doctor updatedDoctor = Doctor.builder().name("Internal Test Doctor").specialization(null).experience(3).email("internalTestdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class, () -> this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }


    @DisplayName("Updating Doctor Using Null Experience")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_Null_positive_Experience_throw_DoctorUpdateException() {
        Doctor updatedDoctor = Doctor.builder().name("Internal Test Doctor").specialization("Neurologist").experience(null).email("internalTestdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class, () -> this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }


    @DisplayName("Updating Doctor Using Negative Experience")
    @Test
    void when_updateDoctorAccountIntoApplication_is_called_with_notNull_name_notNull_specialization_notNull_negative_Experience_throw_DoctorUpdateException() {
        Doctor updatedDoctor = Doctor.builder().name("Internal Test Doctor").specialization("Neurologist").experience(-3).email("internalTestdoc@gmail.com").password("123").build();
        Assertions.assertThrows(DoctorUpdationException.class, () -> this.doctorService.updateDoctorAccountIntoApplication(updatedDoctor));
    }

//    Testcases for delete doctor functionality

    @DisplayName("Deleting Object Using Existing Email")
    @Test
    void when_deleteDoctorAccountFromApplication_is_called_with_notNull_email_notNull_password_return_deleted_object() {
        try {
            Doctor deletedDoctor = this.doctorService.deleteDoctorAccountFromApplication(internalTestDoctor.getEmail(), internalTestDoctor.getPassword());
            Assertions.assertNotNull(deletedDoctor);
        } catch (DoctorDeletionException e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("Deleting Object Using Null Email")
    @Test
    void when_deleteDoctorAccountFromApplication_is_called_with_null_email_notNull_password_return_DoctorDeletionException() {
        Assertions.assertThrows(DoctorDeletionException.class, () -> this.doctorService.deleteDoctorAccountFromApplication(null, internalTestDoctor.getPassword()));
    }


    @DisplayName("Deleting Object Using Null Password")
    @Test
    void when_deleteDoctorAccountFromApplication_is_called_with_notNull_email_null_password_return_DoctorDeletionException() {
        Assertions.assertThrows(DoctorDeletionException.class, () -> this.doctorService.deleteDoctorAccountFromApplication(internalTestDoctor.getEmail(), null));
    }


    @DisplayName("Deleting Object Using Non-Existing Email")
    @Test
    void when_deleteDoctorAccountFromApplication_is_called_with_notNull_nonExisting_email_notNull_password_return_DoctorDeletionException() {
        Assertions.assertThrows(DoctorDeletionException.class, () -> this.doctorService.deleteDoctorAccountFromApplication("nonExisting@gmail.com", internalTestDoctor.getPassword()));
    }


    //Testcases for approving appointments functionality

//    @Test
//    void when_approveAppointment_is_called_with_notNull_appointmentId_return_the_Appointment_object(){
//        Assertions.assertNotNull(this.doctorService.confirmAppointment(internalTestAppointment.getId()));
//    }

    @Test
    void when_approveAppointment_is_called_with_null_appointmentId_throw_DoctorAppointmentConfirmationException(){
        Assertions.assertThrows(DoctorAppointmentConfirmationException.class,()->this.doctorService.confirmAppointment(null));
    }

    @Test
    void when_approveAppointment_is_called_with_notNull_nonExisting_appointmentId_throw_DoctorAppointmentConfirmationException(){
        Assertions.assertThrows(DoctorAppointmentConfirmationException.class,()->this.doctorService.confirmAppointment(1234));
    }





    //Testcases for viewing appointments
    @Test
    void when_getAllAppointments_is_called_with_valid_input_return_list_of_appointments() {
        try {
            Assertions.assertNotNull(this.doctorService.getAllAppointments(internalTestDoctor.getId()));
        } catch (DoctorAppointmentsFetchingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_getAllAppointments_is_called_with_null_doctorID_input_throw_doctorAppointmentsFetchingException() {
        Assertions.assertThrows(DoctorAppointmentsFetchingException.class, () -> this.doctorService.getAllAppointments(null));
    }

    @Test
    void when_getAllAppointments_is_called_with_non_existing_doctorID_input_throw_doctorAppointmentsFetchingException() {
        Assertions.assertThrows(DoctorAppointmentsFetchingException.class, () -> this.doctorService.getAllAppointments(-1));
    }

    @Test
    void when_getAllAppointments_is_called_with_inactive_doctorID_input_throw_doctorAppointmentsFetchingException() {

        try {
            this.doctorService.deleteDoctorAccountFromApplication(internalTestDoctor.getEmail(), internalTestDoctor.getPassword());
        } catch (DoctorDeletionException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(DoctorAppointmentsFetchingException.class, () -> this.doctorService.getAllAppointments(internalTestDoctor.getId()));
    }
  // testcases for viewing previous appointments for Doctor
  @Test
  void when_getPreviousAppointments_is_called_with_valid_input_return_the_List_of_Appointments(){
      try {
          Assertions.assertNotNull(this.doctorService.getAllPreviousAppointments(internalTestDoctor.getId(), LocalDate.of(2024,2,19)));
      } catch (DoctorAppointmentsFetchingException e) {
          throw new RuntimeException(e);
      }
  }

    @Test
    void when_getPreviousAppointments_is_called_with_null_id_input_throw_DoctorAppointmentsFetchingException(){
        Assertions.assertThrows(DoctorAppointmentsFetchingException.class,()->this.doctorService.getAllPreviousAppointments(null,LocalDate.of(2024,2,19)));
    }
    @Test
    void when_getPreviousAppointments_is_called_with_nonExisting_id_input_throw_DoctorAppointmentsFetchingException(){
        Assertions.assertThrows(DoctorAppointmentsFetchingException.class,()->this.doctorService.getAllPreviousAppointments(-1,LocalDate.of(2024,2,19)));
    }

    @Test
    void when_getPreviousAppointments_is_called_with_null_date_input_throw_DoctorAppointmentsFetchingException(){
        Assertions.assertThrows(DoctorAppointmentsFetchingException.class,()->this.doctorService.getAllPreviousAppointments(internalTestDoctor.getId(),null));
    }
    // TEST CASES  FOR FUTURE APPOINTMENTS
    @Test
    void when_getFutureAppointments_is_called_with_valid_input_return_the_List_of_Appointments(){
        try {
            Assertions.assertNotNull(this.doctorService.getAllFutureAppointments(internalTestDoctor.getId(), LocalDate.of(2024,2,19)));
        } catch (DoctorAppointmentsFetchingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void when_getFutureAppointments_is_called_with_null_id_input_throw_DoctorAppointmentsFetchingException(){
        Assertions.assertThrows(DoctorAppointmentsFetchingException.class,()->this.doctorService.getAllFutureAppointments(null,LocalDate.of(2024,2,19)));
    }
    @Test
    void when_getFutureAppointments_is_called_with_nonExisting_id_input_throw_DoctorAppointmentsFetchingException(){
        Assertions.assertThrows(DoctorAppointmentsFetchingException.class,()->this.doctorService.getAllFutureAppointments(-1,LocalDate.of(2024,2,19)));
    }
    @Test
    void when_getFutureAppointments_is_called_with_null_date_input_throw_DoctorAppointmentsFetchingException(){
        Assertions.assertThrows(DoctorAppointmentsFetchingException.class,()->this.doctorService.getAllFutureAppointments(internalTestDoctor.getId(),null));
    }


}
