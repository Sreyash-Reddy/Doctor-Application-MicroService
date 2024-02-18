package com.doctorappointmentapp.doctorapplicationmicroservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    private LocalDate dateOfBirth;

}
