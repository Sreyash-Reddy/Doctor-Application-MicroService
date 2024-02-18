package com.doctorappointmentapp.doctorapplicationmicroservice.appointment;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @GeneratedValue
    @Id
    private Integer appointmentID;
    private String appointmentDescription;
    private Boolean paymentStatus;

    @ManyToOne
    @JsonIgnore
    private Doctor doctor;

    @JsonIgnore
    @ManyToOne
    private Client user;



}

//Client

//com.company.mobileapp.customer.Custom