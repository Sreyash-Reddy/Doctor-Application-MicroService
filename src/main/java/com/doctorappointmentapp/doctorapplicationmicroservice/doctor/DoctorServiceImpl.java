package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public Doctor registerNewDoctorAccountIntoApplication(Doctor doctor) throws DoctorRegistrationException {
        if (doctor == null) throw new DoctorRegistrationException("Null Data Received, Please verify and Register Again");
        if (doctor.getName() == null) throw new DoctorRegistrationException("Doctor's Name Field Cannot Be Null, Please verify and Register Again");
        if (doctor.getSpecialization() == null) throw new DoctorRegistrationException("Doctor's Specialization Field Cannot Be Null, Please verify and Register Again");
        if (doctor.getExperience() == null) throw new DoctorRegistrationException("Doctor's Experience Field Cannot Be Null, Please verify and Register Again");
        if (doctor.getExperience() < 0) throw new DoctorRegistrationException("Doctor's Experience Field Cannot Be Negative, Please verify and Register Again");
        if (doctor.getEmail() == null) throw new DoctorRegistrationException("Doctor's Email Field Cannot Be Null, Please verify and Register Again");
        if (!this.doctorRepository.findByEmail(doctor.getEmail()).isEmpty()) throw new DoctorRegistrationException("Email Entry Already Exists, Please verify and Register Again");
        if (doctor.getPassword() == null) throw new DoctorRegistrationException("Doctor's Password Field Cannot Be Null, Please verify and Register Again");

        return this.doctorRepository.save(doctor);
    }
}
