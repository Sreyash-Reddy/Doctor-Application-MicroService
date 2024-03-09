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
    @Pattern(regexp = "^[a-zA-Z]{5,12}$", message = "Name can only contain letters")
    @Size(min=5,max=12,message="Length should be between 5-12 characters")
    private String name;

    @Email(message = "Enter a valid email")
    private String email;

    private String password;

    @Pattern(regexp = "[1-9][0-9]{9}")
    private String mobileNumber;

    private LocalDate dateOfBirth;

}
