package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorLoginDTO {
    private String email;
    private String password;

}
