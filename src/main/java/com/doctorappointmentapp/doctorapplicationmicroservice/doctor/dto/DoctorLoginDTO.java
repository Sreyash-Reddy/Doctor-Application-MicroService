package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorLoginDTO {

    @Email
    private String email;

    private String password;

}
