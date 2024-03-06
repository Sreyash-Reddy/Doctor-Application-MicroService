package com.doctorappointmentapp.doctorapplicationmicroservice.doctor;

import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Optional<Doctor> findByEmail(String email);

    Optional<Doctor> findDoctorById(Integer Id);
    List<Doctor> findByIsActive(Boolean isActive);
    List<Doctor> findByNameAndIsActive(String doctorName,Boolean isActive);

    List<Doctor> findBySpecializationAndIsActive(String specialization, Boolean isActive);

    List<Doctor> findAllBy();
    @Override
    void deleteAll();

}
