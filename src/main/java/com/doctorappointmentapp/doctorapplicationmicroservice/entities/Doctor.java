package com.doctorappointmentapp.doctorapplicationmicroservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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


    private Boolean isAvailable = true;
}
