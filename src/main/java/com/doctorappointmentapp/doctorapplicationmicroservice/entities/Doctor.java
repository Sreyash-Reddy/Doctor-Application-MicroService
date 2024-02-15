package com.doctorappointmentapp.doctorapplicationmicroservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    //Basic Details
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String specialization;
    private Integer experience;


    private Boolean isAvailable;


}
