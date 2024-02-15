package com.doctorappointmentapp.doctorapplicationmicroservice.service;

import com.doctorappointmentapp.doctorapplicationmicroservice.dataaccesslogic.DoctorRepository;
import com.doctorappointmentapp.doctorapplicationmicroservice.entities.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.exceptions.DoctorRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorApplicationServiceImplementation implements DoctorApplicationService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor registerNewDoctorAccountIntoApplication(Doctor doctor) throws DoctorRegistrationException {
        if (doctor.getName() == null) throw new DoctorRegistrationException("Doctor's Name Field Cannot Be Null, Please verify and Register Again");
        if (doctor.getSpecialization() == null) throw new DoctorRegistrationException("Doctor's Specialization Field Cannot Be Null, Please verify and Register Again");
        if (doctor.getExperience() == null) throw new DoctorRegistrationException("Doctor's Experience Field Cannot Be Null, Please verify and Register Again");
        if (doctor.getExperience() < 0) throw new DoctorRegistrationException("Doctor's Experience Field Cannot Be Negative, Please verify and Register Again");

        return this.doctorRepository.save(doctor);
    }
}
