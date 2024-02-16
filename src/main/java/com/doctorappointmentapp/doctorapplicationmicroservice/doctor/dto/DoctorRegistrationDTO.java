package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorRegistrationDTO {
    private String name;
    private String specialization;
    private Integer experience;
    private String mobileNumber;
    private Double consultancyFee;

    //Authorization
    private String email;
    private String password;
}
