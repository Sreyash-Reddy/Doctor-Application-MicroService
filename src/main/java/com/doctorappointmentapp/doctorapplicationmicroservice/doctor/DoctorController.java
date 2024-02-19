package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto.DoctorDeleteDto;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto.DoctorLoginDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto.DoctorRegistrationDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorDeletionException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorLoginException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorRegistrationException;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.DoctorUpdationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("update/doctor")
    public Doctor updateDoctorAccountIntoApplication(@RequestBody DoctorRegistrationDTO doctorUpdatedDoctorInformation) throws DoctorUpdationException {
        return this.doctorService.updateDoctorAccountIntoApplication(Doctor.builder().name(doctorUpdatedDoctorInformation.getName())
                .specialization(doctorUpdatedDoctorInformation.getSpecialization())
                .experience(doctorUpdatedDoctorInformation.getExperience())
                .mobileNumber(doctorUpdatedDoctorInformation.getMobileNumber())
                .consultancyFee(doctorUpdatedDoctorInformation.getConsultancyFee())
                .email(doctorUpdatedDoctorInformation.getEmail())
                .password(doctorUpdatedDoctorInformation.getPassword())
                .build());
    }



    @DeleteMapping("delete/doctor")
    public Doctor deleteDoctorAccountFromApplication(@RequestBody DoctorDeleteDto doctorDeleteDto) throws DoctorDeletionException {
        return this.doctorService.deleteDoctorAccountFromApplication(doctorDeleteDto.getEmail(),doctorDeleteDto.getPassword());
    }

}
