package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Optional<Doctor> findByEmail(String email);

    @Override
    void deleteAll();

}
