package com.doctorappointmentapp.doctorapplicationmicroservice.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    Optional<Appointment> findByDoctorIDAndAppointmentDateAndAndAppointmentSlot(Integer doctorID,LocalDate appointmentDate, Integer slot);
}
