package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto.DoctorRegistrationDTO;
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
}
