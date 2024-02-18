package com.doctorappointmentapp.doctorapplicationmicroservice.clientTest;


import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.ClientService;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientUpdationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ClientServiceTest {
    @Autowired
    private ClientService clientService;

    private Client internalTestClient =Client.builder().name("Internal Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("123").build();
    private Client externalTestClient =Client.builder().name("Internal Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("externalTestClient@gmail.com").password("123").build();


    @BeforeEach
    void beforeEachTest(){
        clientService.deleteAllClients();
        try {
            clientService.registerNewClientAccountIntoApplication(internalTestClient);
        } catch (ClientRegistrationException e) {
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
        Client client=Client.builder().name(null).dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("testClient@gmail.com").password("123").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client Using Null Date Of Birth")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_name_null_dateOfBirth_notNull_mobileNumber_throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(null).mobileNumber("9876543210").email("testClient@gmail.com").password("123").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client With Date Of Birth Within Last 18 Years")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_name_within_past_18_years_dateOfBirth_notNull_mobileNumber_throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2020,12,31)).mobileNumber("9876543210").email("testClient@gmail.com").password("123").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client Using Null Mobile Number")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_name_notNull_dateOfBirth_null_mobileNumber__throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber(null).email("testClient@gmail.com").password("123").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client Using Null Email")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_null_email_notNull_password_throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email(null).password("123").build();
        Assertions.assertThrows(ClientRegistrationException.class,()->this.clientService.registerNewClientAccountIntoApplication(client));
    }

    @DisplayName("Register Client Using Existing Email")
    @Test
    void when_registerNewClientAccountIntoApplication_is_called_with_notNull_existing_email_notNull_password_throw_ClientRegistrationException(){
        Client client=Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("123").build();
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
        Client testClient=Client.builder().name("Internal Test Client Updated").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("123").build();
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
        Client testClient=Client.builder().name(null).dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("123").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Updating Client Null Date Of Birth")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_notNull_name_null_dateOfBirth_notNull_mobileNumber_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(null).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("123").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Updating Client With Date Of Birth within past 18 years")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_notNull_name_dateOfBirth_within_past_18_years_notNull_mobileNumber_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(LocalDate.of(2020,12,31)).mobileNumber("9876543210").email("internalTestClient@gmail.com").password("123").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Updating Client with Null Mobile Number")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_notNull_name_notNull_dateOfBirth_null_mobileNumber_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber(null).email("internalTestClient@gmail.com").password("123").build();
        Assertions.assertThrows(ClientUpdationException.class,()->this.clientService.updateClientAccountIntoApplication(testClient));
    }

    @DisplayName("Update Client Using Null Email and Password")
    @Test
    void when_updateClientAccountIntoApplication_is_called_with_null_email_notNull_password_throw_ClientUpdationException(){
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email(null).password("123").build();
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
        Client testClient=Client.builder().name("Test Doctor").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("nonExisting@gmail.com").password("123").build();
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

















}
