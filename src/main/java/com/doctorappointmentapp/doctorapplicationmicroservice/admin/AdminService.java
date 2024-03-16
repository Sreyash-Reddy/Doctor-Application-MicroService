package com.doctorappointmentapp.doctorapplicationmicroservice.admin;


import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.AdminLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.ClientDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.admin.exceptions.DoctorDeactivationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.ClientLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;

import java.util.List;

public interface AdminService {
    List<Doctor> getAllDoctors();

    Doctor deactivateDoctor(Integer doctorId) throws DoctorDeactivationException;

    List<Client> getALlClients();

    List<Appointment> getAllAppointments();

    Client deactivateClient(Integer clientId) throws ClientDeactivationException;

    Admin loginAdminAccountIntoApplication(String email, String password) throws AdminLoginException;

}