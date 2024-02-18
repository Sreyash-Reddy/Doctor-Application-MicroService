package com.doctorappointmentapp.doctorapplicationmicroservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientLoginDTO {
    private String email;
    private String password;
}
