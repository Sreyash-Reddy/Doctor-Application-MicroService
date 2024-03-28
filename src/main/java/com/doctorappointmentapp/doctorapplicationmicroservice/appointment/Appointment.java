package com.doctorappointmentapp.doctorapplicationmicroservice.appointment;

import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    private Integer clientID;
    @NotNull
    private Integer doctorID;

//    @JsonIgnore
//    @ManyToOne
////    @Builder.Default
//    private Client client;
//
//    @JsonIgnore
//    @ManyToOne
////    @Builder.Default
//    private Doctor doctor;

//    public Appointment(){
////        this.client=new Client();
////        this.doctor=new Doctor();
//    }

}

//Client

//com.company.mobile app.customer.Custom