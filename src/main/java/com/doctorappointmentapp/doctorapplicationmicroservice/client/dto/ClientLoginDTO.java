package com.doctorappointmentapp.doctorapplicationmicroservice.client.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientLoginDTO {

    @Email(message = "Please enter a valid email")
    private String email;
    private String password;
}
