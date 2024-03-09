package com.doctorappointmentapp.doctorapplicationmicroservice.doctor.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorRegistrationDTO {
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{5,12}$", message = "Name can only contain letters")
    @Size(min=5,max=12,message="Length should be between 5-12 characters")
    private String name;

    @NotNull
    private String specialization;

    @Min(0)
    @Max(100)
    private Integer experience;

    @Pattern(regexp = "[1-9][0-9]{9}")
    private String mobileNumber;

    @Min(0)
    private Double consultancyFee;

    //Authorization
    @Email
    private String email;
    private String password;
}
