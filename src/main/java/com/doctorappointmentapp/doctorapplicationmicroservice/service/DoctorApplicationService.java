package com.doctorappointmentapp.doctorapplicationmicroservice.service;

import com.doctorappointmentapp.doctorapplicationmicroservice.entities.Doctor;

public interface DoctorApplicationService {

    Doctor registerNewDoctorAccountIntoApplication(Doctor doctor);

}
