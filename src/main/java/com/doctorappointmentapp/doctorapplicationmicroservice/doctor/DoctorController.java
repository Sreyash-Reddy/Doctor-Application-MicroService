package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto.DoctorLoginDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto.DoctorRegistrationDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("register/doctor")
    public Doctor registerNewDoctorAccountIntoApplication(@RequestBody DoctorRegistrationDTO newDoctorInputInformation) throws DoctorRegistrationException {
        return this.doctorService.registerNewDoctorAccountIntoApplication(Doctor.builder().name(newDoctorInputInformation.getName())
                .specialization(newDoctorInputInformation.getSpecialization())
                .experience(newDoctorInputInformation.getExperience())
                .mobileNumber(newDoctorInputInformation.getMobileNumber())
                .consultancyFee(newDoctorInputInformation.getConsultancyFee())
                .email(newDoctorInputInformation.getEmail())
                .password(newDoctorInputInformation.getPassword())
                .build());
    }

    @PostMapping("login/doctor")
    public Doctor loginDoctorAccountIntoApplication(@RequestBody DoctorLoginDTO doctorLoginInformation) throws DoctorLoginException {
        return this.doctorService.loginDoctorAccountIntoApplication(doctorLoginInformation.getEmail(), doctorLoginInformation.getPassword());
    }
}
