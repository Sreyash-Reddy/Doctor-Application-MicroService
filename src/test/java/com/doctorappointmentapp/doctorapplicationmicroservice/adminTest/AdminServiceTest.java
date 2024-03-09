package com.doctorappointmentapp.doctorapplicationmicroservice.adminTest;

import com.doctorappointmentapp.doctorapplicationmicroservice.admin.AdminService;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.ClientDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.DoctorDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.ClientRepository;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.ClientService;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorRepository;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.event.CaretListener;
import java.time.LocalDate;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ClientRepository clientRepository;

    Integer DoctorId, ClientId;

    @BeforeEach
    void beforeEachTest(){
        Doctor testDoctor = Doctor.builder().name("Test Doctor").specialization("Cardiologist").experience(5).email("testdoc@gmail.com").password("123").build();
        Client testClient = Client.builder().name("Test Client").dateOfBirth(LocalDate.of(2000,12,31)).mobileNumber("9876543210").email("testclient@gmail.com").password("123").build();

        try {
            DoctorId = this.doctorService.registerNewDoctorAccountIntoApplication(testDoctor).getId();
            ClientId = this.clientService.registerNewClientAccountIntoApplication(testClient).getId();
        } catch (DoctorRegistrationException | ClientRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    //DOCTOR

    @DisplayName("Deactivate Doctor Using Null DoctorId")
    @Test
    void when_deactivateDoctor_is_called_with_null_doctorId_throws_DoctorDeactivationException(){
        Assertions.assertThrows(DoctorDeactivationException.class, ()->this.adminService.deactivateDoctor(null));
    }

    @DisplayName("Deactivate Doctor using DoctorId Not Null")
    @Test
    void when_deactivateDoctor_is_called_with_existing_doctorId_not_null(){
        try {
            Assertions.assertNotNull(this.adminService.deactivateClient(ClientId));
        } catch (ClientDeactivationException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Deactivate Doctor using DoctorId Equal Id")
    @Test
    void when_deactivateDoctor_is_called_with_existing_doctorId_not_throws_DoctorDeactivationException(){
        try {
            Assertions.assertEquals(this.doctorRepository.findDoctorById(DoctorId).get().getId(),this.adminService.deactivateDoctor(DoctorId).getId());
        } catch (DoctorDeactivationException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Deactivate Doctor Using Non-Existing DoctorId")
    @Test
    void when_deactivateDoctor_is_called_with_non_existent_doctorId_throws_DoctorDeactivationException(){
        Assertions.assertThrows(DoctorDeactivationException.class, ()->this.adminService.deactivateDoctor(1000));
    }

    @DisplayName("Deactivate Doctor Before Deactivation Doc is Active")
    @Test
    void when_deactivateDoctor_is_called_but_before_setIsActive_returns_true(){
        Assertions.assertEquals(true, this.doctorRepository.findDoctorById(DoctorId).get().getIsActive());
    }

    @DisplayName("Deactivate Doctor Doc is Inactive")
    @Test
    void when_deactivateDoctor_is_called_doc_is_set_to_inactive(){
        try {
            Assertions.assertEquals(false, this.adminService.deactivateDoctor(DoctorId).getIsActive());
        } catch (DoctorDeactivationException e) {
            throw new RuntimeException(e);
        }
    }

    //CLIENT

    @DisplayName("Deactivate Client Using Null ClientId")
    @Test
    void when_deactivateClient_is_called_with_null_clientId_throws_ClientDeactivationException(){
        Assertions.assertThrows(ClientDeactivationException.class, ()->this.adminService.deactivateClient(null));
    }

    @DisplayName("Deactivate Client using ClientId Not Null")
    @Test
    void when_deactivateClient_is_called_with_existing_clientId_not_null(){
        try {
            Assertions.assertNotNull(this.adminService.deactivateClient(ClientId));
        } catch (ClientDeactivationException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Deactivate Client using ClientId Equal Id")
    @Test
    void when_deactivateClient_is_called_with_existing_clientId_not_throws_DoctorDeactivationException(){
        try {
            Assertions.assertEquals(this.clientRepository.findClientById(ClientId).get().getId(),this.adminService.deactivateClient(ClientId).getId());
        } catch (ClientDeactivationException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Deactivate Client Using Non-Existing ClientId")
    @Test
    void when_deactivateClient_is_called_with_non_existing_clientId_throws_ClientDeactivationException(){
        Assertions.assertThrows(ClientDeactivationException.class, ()->this.adminService.deactivateClient(1000));
    }

    @DisplayName("Deactivate Client Before Deactivation Cient is Active")
    @Test
    void when_deactivateClient_is_called_but_before_setIsActive_returns_true(){
        Assertions.assertEquals(true, this.clientRepository.findClientById(ClientId).get().getIsActive());
    }

    @DisplayName("Deactivate Client Now Client is Inactive")
    @Test
    void when_deactivateClient_is_called_client_is_set_to_inactive(){
        try {
            Assertions.assertEquals(false, this.adminService.deactivateClient(ClientId).getIsActive());
        } catch (ClientDeactivationException e) {
            throw new RuntimeException(e);
        }
    }

}
