package com.doctorappointmentapp.doctorapplicationmicroservice.admin;

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
public class Admin {

    @GeneratedValue
    @Id
    private Integer id;
    private String email;
    private String password;

}
