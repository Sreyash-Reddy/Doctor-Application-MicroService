package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @GeneratedValue
    @Id
    private Integer userID;
    private String name;
    private String email;
    private String password;

    //Personal Info
    private LocalDate dateOfBirth;
    private Integer age;
    //Not including Height and Weight

    //Contact Information
    private String mobileNumber;
    //Not including Address

    private Boolean activityStatus;

    @OneToMany
    private List<Appointment> upcomingAppointmentList = new ArrayList<>();

    @OneToMany//Remove it if we find a good solution
    private List<Appointment> previousAppointmentList = new ArrayList<>();





}
