package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
//import javax.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NonNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{5,12}$", message = "Name can only contain letters")
    @Size(min=5,max=12,message="Length should be between 5-12 characters")
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
