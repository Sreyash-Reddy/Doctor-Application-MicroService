package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorUpdationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

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
        Optional<Doctor> doctorOptional= this.doctorRepository.findByEmail(doctor.getEmail()) ;
        if (doctorOptional.isPresent()){
            Doctor foundDoctor=doctorOptional.get();
            if(foundDoctor.getIsActive()) throw new DoctorRegistrationException("Email Entry Already Exists, Please verify and Register Again");
            else throw new DoctorRegistrationException("Account With Given Email Has Been Deactivated, Please Contact The Admin");
        }
        if (doctor.getPassword() == null) throw new DoctorRegistrationException("Doctor's Password Field Cannot Be Null, Please verify and Register Again");

        return this.doctorRepository.save(doctor);
    }

    @Override
    public Doctor loginDoctorAccountIntoApplication(String email, String password) throws DoctorLoginException {
        if (email == null) throw new DoctorLoginException("Email field cannot be null! Please retry login!");
        if (password == null) throw new DoctorLoginException("Password field cannot be null! Please retry login!");
        Optional<Doctor> doctorDetails = this.doctorRepository.findByEmail(email);
        if (doctorDetails.isEmpty()) throw new DoctorLoginException("Email account does not exist! Please register!");

        Doctor foundDoctor=doctorDetails.get();
        if(!foundDoctor.getIsActive())throw new DoctorLoginException("Account With Given Email Has Been Deactivated, Please Contact The Admin");
        else if (!doctorDetails.get().getPassword().equals(password)) throw new DoctorLoginException("Incorrect password! Please retry login!");

        return doctorDetails.get();
    }


    @Override
    public Doctor updateDoctorAccountIntoApplication(Doctor updatedDoctor) throws DoctorUpdationException {
        if (updatedDoctor == null) throw new DoctorUpdationException("Null Data Received, Please verify and Try Again");
        if (updatedDoctor.getName() == null) throw new DoctorUpdationException("Doctor's Name Field Cannot Be Null, Please verify and Try Again");
        if (updatedDoctor.getSpecialization() == null) throw new DoctorUpdationException("Doctor's Specialization Field Cannot Be Null, Please verify and Try Again");
        if (updatedDoctor.getExperience() == null) throw new DoctorUpdationException("Doctor's Experience Field Cannot Be Null, Please verify and Try Again");
        if (updatedDoctor.getExperience() < 0) throw new DoctorUpdationException("Doctor's Experience Field Cannot Be Negative, Please verify and Try Again");
        if (updatedDoctor.getEmail() == null) throw new DoctorUpdationException("Doctor's Email Field Cannot Be Null, Please verify and Try Again");
        if (this.doctorRepository.findByEmail(updatedDoctor.getEmail()).isEmpty()) throw new DoctorUpdationException("Account With Given Email Does Not Exist, Please verify and Try Again");
        if (updatedDoctor.getPassword() == null) throw new DoctorUpdationException("Doctor's Password Field Cannot Be Null, Please verify and Try Again");

        Optional<Doctor> doctorOptional=this.doctorRepository.findByEmail(updatedDoctor.getEmail());
        Doctor doctorToBeUpdated=doctorOptional.get();

        doctorToBeUpdated.setName(updatedDoctor.getName());
        doctorToBeUpdated.setSpecialization(updatedDoctor.getSpecialization());
        doctorToBeUpdated.setExperience(updatedDoctor.getExperience());
        doctorToBeUpdated.setMobileNumber(updatedDoctor.getMobileNumber());
        doctorToBeUpdated.setConsultancyFee(updatedDoctor.getConsultancyFee());
        doctorToBeUpdated.setPassword(updatedDoctor.getPassword());

        return this.doctorRepository.save(doctorToBeUpdated);
    }

    @Override
    public Doctor deleteDoctorAccountFromApplication(String email) throws DoctorDeletionException {
        if (email == null) throw new DoctorDeletionException("Doctor's Email Field Cannot Be Null, Please verify and Try Again");
        Optional<Doctor> doctorOptional=this.doctorRepository.findByEmail(email);
        if(doctorOptional.isEmpty()) throw new DoctorDeletionException("Account With Given Email Does Not Exist, Please verify and Try Again");
        else{
            Doctor doctorToBeDeleted=doctorOptional.get();
            doctorToBeDeleted.setIsActive(false);
            this.doctorRepository.save(doctorToBeDeleted);
            return doctorToBeDeleted;
        }

    }


}
