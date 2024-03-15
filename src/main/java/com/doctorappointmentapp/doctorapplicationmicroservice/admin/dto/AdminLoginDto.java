package com.doctorappointmentapp.doctorapplicationmicroservice.admin.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminLoginDto {
    @Email(message = "Please enter a valid email ")
    private String email;

    private String password;
}
