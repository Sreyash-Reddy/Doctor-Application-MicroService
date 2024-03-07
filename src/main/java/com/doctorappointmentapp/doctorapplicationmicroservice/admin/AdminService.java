package com.doctorappointmentapp.doctorapplicationmicroservice.admin;


import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.ClientDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.DoctorDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;

import java.util.List;

public interface AdminService {
    List<Doctor> getAllDoctors();

    Doctor deactivateDoctor(Integer doctorId) throws DoctorDeactivationException;

    List<Client> getALlClients();

    Client deactivateClient(Integer clientId) throws ClientDeactivationException;
}
