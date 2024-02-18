package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientUpdationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorUpdationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client registerNewClientAccountIntoApplication(Client client) throws ClientRegistrationException {
        if (client == null) throw new ClientRegistrationException("Null Data Received, Please verify and Register Again");
        if (client.getName() == null) throw new ClientRegistrationException("Client's Name Field Cannot Be Null, Please verify and Register Again");
        if (client.getMobileNumber() == null) throw new ClientRegistrationException("Client's Mobile Number Field Cannot Be Null, Please verify and Register Again");
        if (client.getDateOfBirth() == null) throw new ClientRegistrationException("Client's Date of Birth Field Cannot Be Null, Please verify and Register Again");

        client.getAge();

        if(client.getAge()<18) throw new ClientRegistrationException("Clients Age Should Be At Least 18 years");
        if (client.getEmail() == null) throw new ClientRegistrationException("Client's Email Field Cannot Be Null, Please verify and Register Again");
        Optional<Client> clientOptional= this.clientRepository.findByEmail(client.getEmail()) ;
        if (clientOptional.isPresent()){
            Client foundClient=clientOptional.get();
            if(foundClient.getIsActive()) throw new ClientRegistrationException("Account With Given Email Already Exists, Please verify and Register Again");
            else throw new ClientRegistrationException("Account With Given Email Has Been Deactivated, Please Contact The Admin");
        }
        if (client.getPassword() == null) throw new ClientRegistrationException("Client's Password Field Cannot Be Null, Please verify and Register Again");
//        client.setIsActive(true);
//        client.setPreviousAppointmentList(new ArrayList<>());
//        client.setUpcomingAppointmentList(new ArrayList<>());
        return this.clientRepository.save(client);
    }

    @Override
    public Client loginClientAccountIntoApplication(String email, String password) throws ClientLoginException {
        if (email == null) throw new ClientLoginException("Email field cannot be null! Please retry login!");
        if (password == null) throw new ClientLoginException("Password field cannot be null! Please retry login!");
        Optional<Client> clientDetails = this.clientRepository.findByEmail(email);
        if (clientDetails.isEmpty()) throw new ClientLoginException("Email account does not exist! Please register!");

        Client foundClient=clientDetails.get();
        System.out.println(foundClient.getIsActive());
        if(!foundClient.getIsActive())throw new ClientLoginException("Account With Given Email Has Been Deactivated, Please Contact The Admin");
        else if (!clientDetails.get().getPassword().equals(password)) throw new ClientLoginException("Incorrect password! Please retry login!");

        return clientDetails.get();
    }

    @Override
    public Client updateClientAccountIntoApplication(Client updatedClient) throws ClientUpdationException {
        if (updatedClient == null) throw new ClientUpdationException("Null Data Received, Please verify and Try Again");
        if (updatedClient.getName() == null) throw new ClientUpdationException("Client's Name Field Cannot Be Null, Please verify and Try Again");
        if (updatedClient.getDateOfBirth() == null) throw new ClientUpdationException("Client's Date of Birth Field Cannot Be Null, Please verify and Try Again");
        if (updatedClient.getAge() < 18) throw new ClientUpdationException("Age should be greater than 18");
        if (updatedClient.getMobileNumber()==null) throw new ClientUpdationException("Client's Mobile Number Field Cannot Be Negative, Please verify and Try Again");
        if (updatedClient.getEmail() == null) throw new ClientUpdationException("Client's Email Field Cannot Be Null, Please verify and Try Again");
        if (this.clientRepository.findByEmail(updatedClient.getEmail()).isEmpty()) throw new ClientUpdationException("Account With Given Email Does Not Exist, Please verify and Try Again");
        if (updatedClient.getPassword() == null) throw new ClientUpdationException("Client's Password Field Cannot Be Null, Please verify and Try Again");

        Optional<Client> clientOptional=this.clientRepository.findByEmail(updatedClient.getEmail());
        Client clientToBeUpdated=clientOptional.get();

        clientToBeUpdated.setName(updatedClient.getName());
        clientToBeUpdated.setDateOfBirth(updatedClient.getDateOfBirth());
        clientToBeUpdated.getAge();
        clientToBeUpdated.setMobileNumber(updatedClient.getMobileNumber());
        clientToBeUpdated.setPassword(updatedClient.getPassword());

        return this.clientRepository.save(clientToBeUpdated);
    }

    @Override
    public Client deleteClientAccountFromApplication(String email, String password) throws ClientDeletionException {
        if (email == null) throw new ClientDeletionException("Client's Email Field Cannot Be Null, Please verify and Try Again");
        if(password==null) throw new ClientDeletionException("Client's Password Field Cannot Be Null, Please verify and Try Again");
        Optional<Client> clientOptional=this.clientRepository.findByEmail(email);
        if(clientOptional.isEmpty()) throw new ClientDeletionException("Account With Given Email Does Not Exist, Please verify and Try Again");
        Client clientToBeDeleted=clientOptional.get();
        if(!clientToBeDeleted.getPassword().equals(password)) throw new ClientDeletionException("Incorrect Password, Please Try Again");

        //Should SET isActive to FALSE
        clientToBeDeleted.setIsActive(false);

        this.clientRepository.save(clientToBeDeleted);
        return clientToBeDeleted;
    }

    @Override
    public void deleteAllClients() {
        this.clientRepository.deleteAll();
    }
}
