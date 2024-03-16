package com.doctorappointmentapp.doctorapplicationmicroservice.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminLoginDto {
    @Pattern(regexp = "^[a-zA-Z0-9._+]{1,20}@(gmail\\.com|yahoo\\.com|ford\\.com|outlook\\.com)$", message="Enter a valid email")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\^$*.\\[\\]{}()?\\-\"!@#%&/\\\\,><':;|_~`+=]).{8,16}$", message="Password should contain at least one of each of the following -> lowercase, uppercase, digit, special character")
    private String password;
}
