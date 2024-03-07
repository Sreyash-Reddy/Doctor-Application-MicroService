package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@Entity
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    //Basic Details
    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private String specialization;
    private Integer experience;
    private String mobileNumber;
    private Double consultancyFee;

    @Builder.Default
    private Boolean isActive=true;

    //Authorization
    private String email;
    private String password;

    @Builder.Default
    @OneToMany
    private List<Appointment> appointmentList = new ArrayList<>();

    public Doctor() {
        this.isActive = true;
        this.appointmentList = new ArrayList<>();
    }

}
