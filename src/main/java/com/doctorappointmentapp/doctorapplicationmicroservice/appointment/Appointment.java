package com.doctorappointmentapp.doctorapplicationmicroservice.appointment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @GeneratedValue
    @Id
    private Integer id;
    private String appointmentDescription;

    private Boolean paymentStatus;
    private Boolean doctorConfirmationStatus;

    private LocalDate appointmentDate;
    @Min(1)
    @Max(4)
    private Integer appointmentSlot;

    private Integer clientID;
    private Integer doctorID;
}

//Client

//com.company.mobile app.customer.Custom