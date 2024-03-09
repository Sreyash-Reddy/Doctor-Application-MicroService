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
    @Pattern(regexp = "^[a-zA-Z]{5,12}$", message = "Name can only contain letters")
    @Size(min=5,max=12,message="Length should be between 5-12 characters")
    private String name;

    @NotNull
    private String specialization;

    @Min(0)
    @Max(100)
    private Integer experience;

    @Pattern(regexp = "[1-9][0-9]{9}")
    private String mobileNumber;

    @Min(0)
    private Double consultancyFee;

    @Builder.Default
    private Boolean isActive=true;

    //Authorization
    @Email
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
