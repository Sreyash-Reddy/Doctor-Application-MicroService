package com.doctorappointmentapp.doctorapplicationmicroservice.service;

import com.doctorappointmentapp.doctorapplicationmicroservice.dataaccesslogic.DoctorRepository;
import com.doctorappointmentapp.doctorapplicationmicroservice.entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorApplicationServiceImplementation implements DoctorApplicationService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor registerNewDoctorAccountIntoApplication(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }
}
