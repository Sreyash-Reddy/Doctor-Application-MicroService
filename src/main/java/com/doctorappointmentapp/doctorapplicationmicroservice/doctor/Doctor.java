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

    private Boolean isActive=true;

    //Authorization
    private String email;
    private String password;

    @OneToMany
    private List<Appointment> upcomingAppointmentList = new ArrayList<>();

    @OneToMany//Remove it if we find a good solution
    private List<Appointment> previousAppointmentList = new ArrayList<>();

//    public Doctor(Integer id, String name, String specialization, Integer experience, String mobileNumber, Double consultancyFee, String email, String password) {
//        this.id = id;
//        this.name = name;
//        this.specialization = specialization;
//        this.experience = experience;
//        this.mobileNumber = mobileNumber;
//        this.consultancyFee = consultancyFee;
//        this.isActive=true;
//        this.email = email;
//        this.password = password;
//    }
}
