package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.controllers;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorService;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto.DoctorDeleteDto;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto.DoctorLoginDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto.DoctorRegistrationDTO;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.exceptions.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
//@CrossOrigin(origins = "http://localhost:4200") // Allow this origin
public class DoctorInformationController {
    @Autowired
    private DoctorService doctorService;


    @GetMapping("doctor/{id}")
    public Doctor getDoctorById(@PathVariable Integer id) throws DoctorFetchingException {
        return this.doctorService.getDoctorById(id);
    }

    @PostMapping("sign-up/doctor")
    public Doctor registerNewDoctorAccountIntoApplication(@Valid @RequestBody DoctorRegistrationDTO newDoctorInputInformation) throws DoctorRegistrationException {
        return this.doctorService.registerNewDoctorAccountIntoApplication(Doctor.builder().name(newDoctorInputInformation.getName())
                .specialization(newDoctorInputInformation.getSpecialization())
                .experience(newDoctorInputInformation.getExperience())
                .mobileNumber(newDoctorInputInformation.getMobileNumber())
                .consultancyFee(newDoctorInputInformation.getConsultancyFee())
                .email(newDoctorInputInformation.getEmail())
                .password(newDoctorInputInformation.getPassword())
                .build());
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("login/doctor")
    public Doctor loginDoctorAccountIntoApplication(@Valid @RequestBody DoctorLoginDTO doctorLoginInformation) throws DoctorLoginException {
        return this.doctorService.loginDoctorAccountIntoApplication(doctorLoginInformation.getEmail(), doctorLoginInformation.getPassword());
    }

    @PutMapping("update-account/doctor")
    public Doctor updateDoctorAccountIntoApplication(@Valid @RequestBody DoctorRegistrationDTO doctorUpdatedDoctorInformation) throws DoctorUpdationException {
        return this.doctorService.updateDoctorAccountIntoApplication(Doctor.builder().name(doctorUpdatedDoctorInformation.getName())
                .specialization(doctorUpdatedDoctorInformation.getSpecialization())
                .experience(doctorUpdatedDoctorInformation.getExperience())
                .mobileNumber(doctorUpdatedDoctorInformation.getMobileNumber())
                .consultancyFee(doctorUpdatedDoctorInformation.getConsultancyFee())
                .email(doctorUpdatedDoctorInformation.getEmail())
                .password(doctorUpdatedDoctorInformation.getPassword())
                .build());
    }



    @DeleteMapping("delete-account/doctor")
    public Doctor deleteDoctorAccountFromApplication(@Valid @RequestBody DoctorDeleteDto doctorDeleteDto) throws DoctorDeletionException {
        return this.doctorService.deleteDoctorAccountFromApplication(doctorDeleteDto.getEmail(),doctorDeleteDto.getPassword());
    }

}
