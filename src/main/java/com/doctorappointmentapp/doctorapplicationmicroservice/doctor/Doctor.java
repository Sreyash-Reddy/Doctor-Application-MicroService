package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
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

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name can only contain letters")
    @Size(min=3,max=20,message="Length should be between 3-20 characters")
    private String name;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$",message = "Specialization can only contain letters with size 3-20 characters")
    private String specialization;

    @Min(0)
    @Max(100)
    private Integer experience;

    @Pattern(regexp = "[1-9][0-9]{9}",message = "Enter a 10 digit mobile number")
    private String mobileNumber;

    @Min(0)
    private Double consultancyFee;

    @Builder.Default
    private Boolean isActive=true;

    //Authorization
    @Pattern(regexp = "^[a-zA-Z0-9._+]{1,20}@(gmail\\.com|yahoo\\.com|ford\\.com|outlook\\.com)$", message="Enter a valid email")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\^$*.\\[\\]{}()?\\-\"!@#%&/\\\\,><':;|_~`+=]).{8,16}$", message="Password should contain at least one of each of the following -> lowercase, uppercase, digit, special character")
    private String password;

    @Builder.Default
    @OneToMany
    private List<Appointment> appointmentList = new ArrayList<>();

    public Doctor() {
        this.isActive = true;
        this.appointmentList = new ArrayList<>();
    }

}
