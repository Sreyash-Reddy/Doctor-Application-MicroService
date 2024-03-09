package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
//import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

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

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{5,12}$", message = "Name can only contain letters")
    @Size(min=5,max=12,message="Length should be between 5-12 characters")
    private String name;

    @Email
    private String email;

    private String password;

    //Personal Info
    private LocalDate dateOfBirth;

    @Min(18)
    @Max(120)
    private Integer age;
    //Not including Height and Weight

    //Contact Information
    @Pattern(regexp = "[1-9][0-9]{9}")
    private String mobileNumber;
    //Not including Address

    @Builder.Default
    private Boolean isActive =true;

    @Builder.Default
    @OneToMany
    private List<Appointment> appointmentList = new ArrayList<>();

    public Client() {
        this.isActive =true;
        this.appointmentList=new ArrayList<>();
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
