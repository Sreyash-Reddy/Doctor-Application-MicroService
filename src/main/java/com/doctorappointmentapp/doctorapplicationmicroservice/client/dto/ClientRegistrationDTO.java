package com.doctorappointmentapp.doctorapplicationmicroservice.client.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRegistrationDTO {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name can only contain letters")
    @Size(min=3,max=20,message="Length should be between 3-20 characters")
    private String name;


    @Pattern(regexp = "^[a-zA-Z0-9._+]{1,20}@(gmail\\.com|yahoo\\.com|ford\\.com|outlook\\.com)$", message="Enter a valid email")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\^$*.\\[\\]{}()?\\-\"!@#%&/\\\\,><':;|_~`+=]).{8,16}$", message="Password should contain at least one of each of the following -> lowercase, uppercase, digit, special character")
    private String password;

    @Pattern(regexp = "[1-9][0-9]{9}",message = "Enter a 10 digit mobile number")
    private String mobileNumber;

    private LocalDate dateOfBirth;

}
