package com.doctorappointmentapp.doctorapplicationmicroservice.clientTest;


import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.ClientService;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.*;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ClientServiceTest {
    @Autowired
    private ClientService clientService;

    @Autowired
    private DoctorService doctorService;

    private Client internalTestClient =Client.builder().name("Internal Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("Aa@1Aa@1").build();
    private Client externalTestClient =Client.builder().name("External Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("externalTestClient@gmail.com").password("Aa@1Aa@1").build();

    private Doctor internalTestDoctor = Doctor.builder().name("Internal Test Doctor").specialization("Neurologist").experience(3).consultancyFee(500.0).mobileNumber("9988776655").email("internalTestdoc@gmail.com").password("Aa@1Aa@1").build();

    private Doctor externalTestDoctor = Doctor.builder().name("External Test Doctor").specialization("Neurologist").experience(3).consultancyFee(500.0).mobileNumber("9988776655").email("externalTestdoc@gmail.com").password("Aa@1Aa@1").build();


    @BeforeEach
    void beforeEachTest(){
        clientService.deleteAllClients();
        doctorService.deleteAllDoctors();
        clientService.deleteAllAppointments();
        try {
            Client testClientResponse=clientService.registerNewClientAccountIntoApplication(internalTestClient);
            Doctor testDoctorResponse=doctorService.registerNewDoctorAccountIntoApplication(internalTestDoctor);
        } catch (ClientRegistrationException | DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }


    //Test cases for register client functionality

    @DisplayName("Register Client With Correct Details")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_name_notNull_dateOfBirth_notNull_mobileNumber_return_object(){
        try {
            Assertions.assertNotNull(this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
        } catch (ClientRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Register Client Using Null Object")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_null_Client_object_throw_ClientRegistrationException(){
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(null));
    }

    @DisplayName("Register Client Using Null Name")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_null_name_notNull_dateOfBirth_notNull_mobileNumber_throw_ClientRegistrationException(){
        Client client=Client.builder().name(null).dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("testClient@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client Using Null Date Of Birth")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_name_null_dateOfBirth_notNull_mobileNumber_throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(null).mobileNumber("9876543210").email("testClient@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client With Date Of Birth Within Last 18 Years")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_name_within_past_18_years_dateOfBirth_notNull_mobileNumber_throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2020,12,31)).mobileNumber("9876543210").email("testClient@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client Using Null Mobile Number")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_name_notNull_dateOfBirth_null_mobileNumber__throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber(null).email("testClient@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client Using Null Email")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_null_email_notNull_password_throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email(null).password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client Using Existing Email")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_existing_email_notNull_password_throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client Using Null Password")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_nonExisting_email_null_password_throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("nonExisting@gmail.com").password(null).build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }




    //Testcases for login client functionality
    @DisplayName("Login Client Using Correct Email and Password")
    @Test
    void when_loginClientAccountIntoApplication_is_called_with_notNull_email_notNull_password_return_Client_object(){
        try {
            Assertions.assertNotNull(this.clientService.loginClientAccountIntoApplication(internalTestClient.getEmail(), internalTestClient.getPassword()));
        } catch (ClientLoginException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Login Client Using Null Email and Password")
    @Test
    void when_loginClientAccountIntoApplication_is_called_with_null_email_notNull_password_throw_ClientLoginException(){
        Assertions.assertThrows(ClientLoginException.class,()->this.clientService.loginClientAccountIntoApplication(null, internalTestClient.getPassword()));
    }

    @DisplayName("Login Client Using Email and Null Password")
    @Test
    void when_loginClientAccountIntoApplication_is_called_with_notNull_email_null_password_throw_ClientLoginException(){
        Assertions.assertThrows(ClientLoginException.class,()->this.clientService.loginClientAccountIntoApplication(internalTestClient.getEmail(), null));
    }

    @DisplayName("Login Client Using Non Existing Email and Password")
    @Test
    void when_loginClientAccountIntoApplication_is_called_with_notNull_nonExisting_email_notNull_password_throw_ClientLoginException(){
        Assertions.assertThrows(ClientLoginException.class,()->this.clientService.loginClientAccountIntoApplication("nonExisting@gmail.com", internalTestClient.getPassword()));
    }

    @DisplayName("Login Client Using Incorrect Password")
    @Test
    void when_loginClientAccountIntoApplication_is_called_with_notNull_existing_email_incorrect_password_throw_ClientLoginException(){
        Assertions.assertThrows(ClientLoginException.class,()->this.clientService.loginClientAccountIntoApplication(internalTestClient.getEmail(), "wrongPassword"));
    }

    //Test cases for Update Client Functionality

    @DisplayName("Updating Client Giving All Details")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_notNull_name_notNull_dateOfBirth_notNull_mobileNumber_return_object(){
        Client testClient=Client.builder().name("Client Updated").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("Aa@1Aa@1").build();
        try {
            Assertions.assertNotNull(this.clientService.updateClientAccountIntoApplication(testClient));
        } catch (ClientUpdationException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Updating Client Giving Null Object")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_null_Client_object_throw_ClientUpdationException(){
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(null));
    }

    @DisplayName("Updating Client Null Name")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_null_name_notNull_dateOfBirth_notNull_mobileNumber_throw_ClientUpdationException(){
        Client testClient=Client.builder().name(null).dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Updating Client Null Date Of Birth")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_notNull_name_null_dateOfBirth_notNull_mobileNumber_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(null).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Updating Client With Date Of Birth within past 18 years")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_notNull_name_dateOfBirth_within_past_18_years_notNull_mobileNumber_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(LocalDate.of(2020,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Updating Client with Null Mobile Number")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_notNull_name_notNull_dateOfBirth_null_mobileNumber_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber(null).email("internalTestClient@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Update Client Using Null Email and Password")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_null_email_notNull_password_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email(null).password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Update Client Using Email and Null Password")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_notNull_email_null_password_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password(null).build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Update Client Using Non-Existing Email")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_notNull_nonExisting_email_notNull_password_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("nonExisting@gmail.com").password("Aa@1Aa@1").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    //Test cases for Delete Client Functionality
    @DisplayName("Deleting Client With Correct Email and Password")
    @Test
    void when_deleteClientAccountFromApplication_is_called_with_notNull_email_notNull_password_return_deleted_Client_object(){
        try {
            Assertions.assertNotNull(this.clientService.deleteClientAccountFromApplication(internalTestClient.getEmail(),internalTestClient.getPassword()));
        } catch (ClientDeletionException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Deleting Client With Null Email")
    @Test
    void when_deleteClientAccountFromApplication_is_called_with_null_email_notNull_password_throw_ClientDeletionException(){
       Assertions.assertThrows(ClientDeletionException.class,()->this.clientService.deleteClientAccountFromApplication(null,internalTestClient.getPassword()));
    }

    @DisplayName("Deleting Client With Null Password")
    @Test
    void when_deleteClientAccountFromApplication_is_called_with_notNull_email_null_password_throw_ClientDeletionException(){
        Assertions.assertThrows(ClientDeletionException.class,()->this.clientService.deleteClientAccountFromApplication(internalTestClient.getEmail(),null));
    }

    @DisplayName("Deleting Client With Non-Existing Email")
    @Test
    void when_deleteClientAccountFromApplication_is_called_with_notNull_nonExisting_email_notNull_password_throw_ClientDeletionException(){
        Assertions.assertThrows(ClientDeletionException.class,()->this.clientService.deleteClientAccountFromApplication("nonExisting@gamil.com",internalTestClient.getPassword()));
    }

    @DisplayName("Deleting Client With Incorrect Password")
    @Test
    void when_deleteClientAccountFromApplication_is_called_with_notNull_email_incorrect_password_throw_ClientDeletionException(){
        Assertions.assertThrows(ClientDeletionException.class,()->this.clientService.deleteClientAccountFromApplication(internalTestClient.getEmail(),"wrongPassword"));
    }


    // TEST CASES FOR SEARCHING AVAILABLE DOCTORS FUNCTIONALITY
    @Test
    void when_getAvailableDoctors_is_called_return_list_of_available_doctors() {
        Assertions.assertNotNull(this.clientService.getAvailableDoctors());
    }
    @Test
    void when_getAvailableDoctorsByName_is_called_with_notNull_name_return_list_of_Doctors(){
        try {
            Assertions.assertNotNull(this.clientService.getAvailableDoctorsByName("doc"));
        } catch (ClientDoctorSearchingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void when_getAvailableDoctorsByName_is_called_with_null_name_throw_ClientDoctorSearchingException(){
        Assertions.assertThrows(ClientDoctorSearchingException.class,()->this.clientService.getAvailableDoctorsByName(null));
    }
    @Test
    void when_getAvailableDoctorsByName_is_called_with_blank_name_throw_ClientDoctorSearchingException(){
        Assertions.assertThrows(ClientDoctorSearchingException.class,()->this.clientService.getAvailableDoctorsByName(""));
    }

    //TEST CASES FOR DOCTOR SPECIALIZATIONS
    @Test
    void when_getAllAvailableDoctorsBySpecialization_is_called_with_valid_input_returns_list_of_doctors(){
        try {
            Assertions.assertNotNull(this.clientService.getAllAvailableDoctorsBySpecialization("Physician"));
        } catch (ClientDoctorSearchingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_getAllAvailableDoctorsBySpecialization_is_called_with_null_specialization_throws_ClientDoctorSearchingException() {
        Assertions.assertThrows(ClientDoctorSearchingException.class, ()->this.clientService.getAllAvailableDoctorsBySpecialization(null));
    }

    @Test
    void when_getAllAvailableDoctorsBySpecialization_is_called_with_blank_specialization_throws_ClientDoctorSearchingException() {
        Assertions.assertThrows(ClientDoctorSearchingException.class, ()->this.clientService.getAllAvailableDoctorsBySpecialization(""));
    }

    //DOCTOR SORTING TESTCASES BY ATTRIBUTE
    @Test
    void when_getAllAvailableDoctorsSortedBy_is_called_with_valid_attribute_return_sorted_list_of_doctors() {
        try {
            Assertions.assertNotNull(this.clientService.getAllAvailableDoctorsSortedBy("experience"));
            Assertions.assertNotNull(this.clientService.getAllAvailableDoctorsSortedBy("consultancy-fee"));
            Assertions.assertNotNull(this.clientService.getAllAvailableDoctorsSortedBy("name"));
            Assertions.assertNotNull(this.clientService.getAllAvailableDoctorsSortedBy("specialization"));
        } catch (ClientDoctorSearchingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_getAllAvailableDoctorsSortedBy_is_called_with_invalid_attribute_throws_ClientDoctorSearchingException() {
        Assertions.assertThrows(ClientDoctorSearchingException.class, ()->this.clientService.getAllAvailableDoctorsSortedBy("wrong-attribute"));
    }
    //Client Appointment Functionality Test Cases

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_valid_inputData_return_Appointment_Object(){
        try {
            Client testClientResponse;
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test Appointment")
                    .paymentStatus(false)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(1)
                    .clientID(testClientResponse.getId())
                    .doctorID(testDoctorResponse.getId())
                    .build();
            Assertions.assertNotNull(this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));
        } catch (ClientAppointmentBookingException | ClientRegistrationException | DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void when_bookAppointmentInClientApplication_is_called_with_null_clientID_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test Appointment")
                    .paymentStatus(false)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(1)
                    .clientID(null)
                    .doctorID(testDoctorResponse.getId())
                    .build();

            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));

        } catch ( DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_nonExisting_clientID_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test Appointment")
                    .paymentStatus(false)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(1)
                    .clientID(-1)
                    .doctorID(testDoctorResponse.getId())
                    .build();

            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));

        } catch ( DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_null_doctorID_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Client testClientResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test Appointment")
                    .paymentStatus(false)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(1)
                    .clientID(testClientResponse.getId())
                    .doctorID(null)
                    .build();

            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));

        } catch ( ClientRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_nonExisting_doctorID_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Client testClientResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test Appointment")
                    .paymentStatus(false)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(1)
                    .clientID(testClientResponse.getId())
                    .doctorID(-1)
                    .build();

            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));

        } catch ( ClientRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_null_appointmentDescription_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Client testClientResponse;
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription(null)
                    .paymentStatus(false)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(1)
                    .clientID(testClientResponse.getId())
                    .doctorID(testDoctorResponse.getId())
                    .build();
            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));
        } catch (ClientRegistrationException | DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_null_paymentStatus_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Client testClientResponse;
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test")
                    .paymentStatus(null)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(1)
                    .clientID(testClientResponse.getId())
                    .doctorID(testDoctorResponse.getId())
                    .build();
            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));
        } catch (ClientRegistrationException | DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_null_doctorConfirmationStatus_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Client testClientResponse;
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test")
                    .paymentStatus(false)
                    .doctorConfirmationStatus(null)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(1)
                    .clientID(testClientResponse.getId())
                    .doctorID(testDoctorResponse.getId())
                    .build();
            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));
        } catch (ClientRegistrationException | DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_null_appointmentDate_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Client testClientResponse;
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test")
                    .paymentStatus(false)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(null)
                    .appointmentSlot(1)
                    .clientID(testClientResponse.getId())
                    .doctorID(testDoctorResponse.getId())
                    .build();
            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));
        } catch (ClientRegistrationException | DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_null_slotBooked_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Client testClientResponse;
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test")
                    .paymentStatus(false)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(null)
                    .clientID(testClientResponse.getId())
                    .doctorID(testDoctorResponse.getId())
                    .build();
            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));
        } catch (ClientRegistrationException | DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_negative_slotBooked_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Client testClientResponse;
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test")
                    .paymentStatus(null)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(-1)
                    .clientID(testClientResponse.getId())
                    .doctorID(testDoctorResponse.getId())
                    .build();
            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));
        } catch (ClientRegistrationException | DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    void when_bookAppointmentInClientApplication_is_called_with_overLimit_slotBooked_valid_inputData_throw_ClientAppointmentBookingException(){
        try {
            Client testClientResponse;
            Doctor testDoctorResponse;
            Assertions.assertNotNull(testClientResponse = this.clientService.registerNewClientAccountIntoApplication(externalTestClient));
            Assertions.assertNotNull(testDoctorResponse = this.doctorService.registerNewDoctorAccountIntoApplication(externalTestDoctor));
            Appointment appointment = Appointment.builder()
                    .appointmentDescription("Test")
                    .paymentStatus(null)
                    .doctorConfirmationStatus(false)
                    .appointmentDate(LocalDate.of(2024,2,20))
                    .appointmentSlot(9)
                    .clientID(testClientResponse.getId())
                    .doctorID(testDoctorResponse.getId())
                    .build();
            Assertions.assertThrows(ClientAppointmentBookingException.class,() ->this.clientService.bookAppointmentInClientApplication(appointment , LocalDate.of(2024,2,19)));
        } catch (ClientRegistrationException | DoctorRegistrationException e) {
            throw new RuntimeException(e);
        }
    }
    //TEST CASES FOR APPOINTMENT PAYMENTS
    @Test
    void when_makePaymentForAppointment_is_called_with_null_appointmentID_throw_ClientAppointmentPaymentException() {
        Assertions.assertThrows(ClientAppointmentPaymentException.class,()->this.clientService.makePaymentForAppointment(null));
    }
   @Test
    void when_makePaymentForAppointment_is_called_with_notNull_nonExisting_appointmentID_throw_ClientAppointmentPaymentException() {
        Assertions.assertThrows(ClientAppointmentPaymentException.class,()->this.clientService.makePaymentForAppointment(1234));
    }
//    @Test
//    void when_makePaymentForAppointment_is_called_with_notNull_already_paid_appointmentID_throw_ClientAppointmentPaymentException() {
//        try {
//            Assertions.assertNotNull(this.clientService.makePaymentForAppointment(internalAppointment.getId()));
//            Assertions.assertThrows(ClientAppointmentPaymentException.class,()->this.clientService.makePaymentForAppointment(internalAppointment.getId()));
//        } catch (ClientAppointmentPaymentException e) {
//            throw new RuntimeException(e);
//        } }

    //TEST CASES FOR GETTING APPOINTMENTS

    @Test
    void when_getAllAppointments_is_called_with_valid_input_return_the_List_of_Appointments(){
        try {
            Assertions.assertNotNull(this.clientService.getAllAppointments(internalTestClient.getId()));
        } catch (ClientAppointmentsFetchingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_getAllAppointments_is_called_with_null_id_input_throw_ClientAppointmentsFetchingException(){
        Assertions.assertThrows(ClientAppointmentsFetchingException.class,()->this.clientService.getAllAppointments(null));
    }

    @Test
    void when_getAllAppointments_is_called_with_non_Existing_id_input_throw_ClientAppointmentsFetchingException(){
        Assertions.assertThrows(ClientAppointmentsFetchingException.class,()->this.clientService.getAllAppointments(-1));
    }

    //TESTCASES FOR GETTING ALL PREVIOUS APPOINTMENTS
    @Test
    void when_getPreviousAppointments_is_called_with_valid_input_return_the_List_of_Appointments(){
        try {
            Assertions.assertNotNull(this.clientService.getAllPreviousAppointments(internalTestClient.getId(),LocalDate.of(2024,2,19)));
        } catch (ClientAppointmentsFetchingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_getPreviousAppointments_is_called_with_null_id_input_throw_ClientAppointmentsFetchingException(){
        Assertions.assertThrows(ClientAppointmentsFetchingException.class,()->this.clientService.getAllPreviousAppointments(null,LocalDate.of(2024,2,19)));
    }

    @Test
    void when_getPreviousAppointments_is_called_with_nonExisting_id_input_throw_ClientAppointmentsFetchingException(){
        Assertions.assertThrows(ClientAppointmentsFetchingException.class,()->this.clientService.getAllPreviousAppointments(-1,LocalDate.of(2024,2,19)));
    }

    @Test
    void when_getPreviousAppointments_is_called_with_null_date_input_throw_ClientAppointmentsFetchingException(){
        Assertions.assertThrows(ClientAppointmentsFetchingException.class,()->this.clientService.getAllPreviousAppointments(internalTestClient.getId(),null));
    }
// TEST CASES FOR FUTURE APPOINTMENTS
    @Test
    void when_getFutureAppointments_is_called_with_valid_input_return_the_List_of_Appointments(){
        try {
            Assertions.assertNotNull(this.clientService.getAllPreviousAppointments(internalTestClient.getId(),LocalDate.of(2024,2,19)));
        } catch (ClientAppointmentsFetchingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void when_getFutureAppointments_is_called_with_null_id_input_throw_ClientAppointmentsFetchingException(){
        Assertions.assertThrows(ClientAppointmentsFetchingException.class,()->this.clientService.getAllPreviousAppointments(null,LocalDate.of(2024,2,19)));
    }

    @Test
    void when_getFutureAppointments_is_called_with_nonExisting_id_input_throw_ClientAppointmentsFetchingException(){
        Assertions.assertThrows(ClientAppointmentsFetchingException.class,()->this.clientService.getAllPreviousAppointments(-1,LocalDate.of(2024,2,19)));
    }
    @Test
    void when_getFutureAppointments_is_called_with_null_date_input_throw_ClientAppointmentsFetchingException(){
        Assertions.assertThrows(ClientAppointmentsFetchingException.class,()->this.clientService.getAllPreviousAppointments(internalTestClient.getId(),null));
    }



}
