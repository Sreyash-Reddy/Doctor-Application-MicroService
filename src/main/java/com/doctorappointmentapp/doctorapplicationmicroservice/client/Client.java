package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @GeneratedValue
    @Id
    private Integer id;
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

    @Builder.Default
    private Boolean isActive =true;

    @Builder.Default
    @OneToMany
    private List<Appointment> upcomingAppointmentList = new ArrayList<>();

    @Builder.Default
    @OneToMany//Remove it if we find a good solution
    private List<Appointment> previousAppointmentList = new ArrayList<>();

    public Client() {
        this.isActive =true;
        this.upcomingAppointmentList=new ArrayList<>();
        this.previousAppointmentList=new ArrayList<>();
    }

    public Integer getAge() {
        LocalDate dateOfBirth = this.getDateOfBirth();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        Integer ageInYears = period.getYears();
        this.setAge(ageInYears);
        return ageInYears;
    }
}
