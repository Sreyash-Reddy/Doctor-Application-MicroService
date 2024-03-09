package com.doctorappointmentapp.doctorapplicationmicroservice.admin.controller;

import com.doctorappointmentapp.doctorapplicationmicroservice.admin.Admin;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.AdminService;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.ClientDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.DoctorDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.ClientService;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    ClientService clientService;

    @Autowired
    AdminService adminService;

    @GetMapping("doctors")
    public List<Doctor> getAllDoctors(){
        return this.adminService.getAllDoctors();
    }

    @DeleteMapping("deactivate/doctor/doctor-id={doctorId}")
    public Doctor deactivateDoctor(@PathVariable Integer doctorId) throws DoctorDeactivationException {
        return this.adminService.deactivateDoctor(doctorId);
    }

    @GetMapping("clients")
    public List<Client> getALlClients(){
        return this.adminService.getALlClients();
    }

    @DeleteMapping("deactivate/client/client-id={clientId}")
    public Client deactivateClient(@PathVariable Integer clientId) throws ClientDeactivationException {
        return this.adminService.deactivateClient(clientId);
    }




}