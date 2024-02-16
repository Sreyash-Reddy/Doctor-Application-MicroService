package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
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

    //Authorization
    private String email;
    private String password;

    @OneToMany
    private List<Appointment> upcomingAppointmentList = new ArrayList<>();

    @OneToMany//Remove it if we find a good solution
    private List<Appointment> previousAppointmentList = new ArrayList<>();
}
