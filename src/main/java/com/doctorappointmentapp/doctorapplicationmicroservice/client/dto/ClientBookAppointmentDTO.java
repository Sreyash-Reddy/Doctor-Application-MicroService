package com.doctorappointmentapp.doctorapplicationmicroservice.client.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientBookAppointmentDTO {

    private LocalDate bookingDate;

    @NotNull
    private Integer clientID;

    @NotNull
    private Integer doctorID;
    private String appointmentDescription;

    private LocalDate appointmentDate;
    @Min(1)
    @Max(4)
    private Integer appointmentSlot;

}
