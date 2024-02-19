package com.doctorappointmentapp.doctorapplicationmicroservice.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    Optional<Appointment> findByDoctorIDAndAppointmentDateAndAndAppointmentSlot(Integer doctorID,LocalDate appointmentDate, Integer slot);

    List<Appointment> findAppointmentByClientID(Integer clientID);

    List<Appointment> findAppointmentByDoctorID(Integer doctorID);
}
