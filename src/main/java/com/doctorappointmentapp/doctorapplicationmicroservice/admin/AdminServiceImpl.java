package com.doctorappointmentapp.doctorapplicationmicroservice.admin;


import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.ClientDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.DoctorDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.ClientRepository;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor deactivateDoctor(Integer doctorId) throws DoctorDeactivationException {
        if(doctorId==null) throw new DoctorDeactivationException("Doctor Id cannot be Null");
        Optional<Doctor> doctorOptional=this.doctorRepository.findDoctorById(doctorId);
        if(doctorOptional.isEmpty()) throw  new DoctorDeactivationException("Doctor with given Id does not exist, please try again");
        Doctor foundDoctor=doctorOptional.get();

        foundDoctor.setIsActive(false);

        this.doctorRepository.save(foundDoctor);
        return foundDoctor;
    }

    @Override
    public List<Client> getALlClients() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client deactivateClient(Integer clientId) throws ClientDeactivationException {
        if(clientId==null) throw new ClientDeactivationException("Client Id cannot be Null");
        Optional<Client> clientOptional=this.clientRepository.findClientById(clientId);
        if(clientOptional.isEmpty()) throw  new ClientDeactivationException("Client with given Id does not exist, please try again");
        Client foundClient=clientOptional.get();

        foundClient.setIsActive(false);

        this.clientRepository.save(foundClient);
        return foundClient;
    }
}
