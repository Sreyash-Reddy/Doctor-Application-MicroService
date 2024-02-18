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
public class ClientBookAppointmentDTO {

    private LocalDate bookingDate;

    private Integer clientID;
    private Integer doctorID;
    private String appointmentDescription;

    private LocalDate appointmentDate;
    private Integer appointmentSlot;

}
