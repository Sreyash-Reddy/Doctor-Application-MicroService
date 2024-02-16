package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Override
    public Doctor loginDoctorAccountIntoApplication(String email, String password) throws DoctorLoginException {
        if (email == null) throw new DoctorLoginException("Email field cannot be null! Please retry login!");
        if (password == null) throw new DoctorLoginException("Password field cannot be null! Please retry login!");
        List<Doctor> doctorDetails = this.doctorRepository.findByEmail(email);
        if (doctorDetails.isEmpty()) throw new DoctorLoginException("Email account does not exist! Please register!");
//        System.out.println(doctorDetails.get(0).getPassword()+password);
        if (!doctorDetails.get(0).getPassword().equals(password)) throw new DoctorLoginException("Incorrect password! Please retry login! Actual:"+doctorDetails.get(0).getPassword()+"Given: "+password);

        return doctorDetails.get(0);
    }


}
