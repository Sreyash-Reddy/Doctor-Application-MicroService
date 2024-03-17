package com.doctorappointmentapp.doctorapplicationmicroservice.client;

import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.Appointment;
import com.doctorappointmentapp.doctorapplicationmicroservice.appointment.AppointmentRepository;
import com.doctorappointmentapp.doctorapplicationmicroservice.client.exceptions.*;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.Doctor;
import com.doctorappointmentapp.doctorapplicationmicroservice.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Client registerNewClientAccountIntoApplication(Client client) throws ClientRegistrationException {
        if (client == null) throw new ClientRegistrationException("Null Data Received, Please verify and Register Again");
        if (client.getName() == null) throw new ClientRegistrationException("Client's Name Field Cannot Be Null, Please verify and Register Again");
        if (client.getMobileNumber() == null) throw new ClientRegistrationException("Client's Mobile Number Field Cannot Be Null, Please verify and Register Again");
        if (client.getDateOfBirth() == null) throw new ClientRegistrationException("Client's Date of Birth Field Cannot Be Null, Please verify and Register Again");

        client.getAge();

        if(client.getAge()<18) throw new ClientRegistrationException("Client's Age Should Be At Least 18 years");
        if (client.getEmail() == null) throw new ClientRegistrationException("Client's Email Field Cannot Be Null, Please verify and Register Again");
        Optional<Client> clientOptional= this.clientRepository.findByEmail(client.getEmail()) ;
        if (clientOptional.isPresent()){
            Client foundClient=clientOptional.get();
            if(foundClient.getIsActive()) throw new ClientRegistrationException("Account With Given Email Already Exists, Please verify and Register Again");
            else throw new ClientRegistrationException("Account With Given Email Has Been Deactivated, Please Contact The Admin");
        }
        if (client.getPassword() == null) throw new ClientRegistrationException("Client's Password Field Cannot Be Null, Please verify and Register Again");
        return this.clientRepository.save(client);
    }

    @Override
    public Client loginClientAccountIntoApplication(String email, String password) throws ClientLoginException {
        if (email == null) throw new ClientLoginException("Email field cannot be null! Please retry login!");
        if (password == null) throw new ClientLoginException("Password field cannot be null! Please retry login!");
        Optional<Client> clientDetails = this.clientRepository.findByEmail(email);
        if (clientDetails.isEmpty()) throw new ClientLoginException("Email account does not exist! Please register!");

        Client foundClient=clientDetails.get();
        System.out.println(foundClient.getIsActive());
        if(!foundClient.getIsActive())throw new ClientLoginException("Account With Given Email Has Been Deactivated, Please Contact The Admin");
        else if (!clientDetails.get().getPassword().equals(password)) throw new ClientLoginException("Incorrect password! Please retry login!");

        return clientDetails.get();
    }

    @Override
    public Client updateClientAccountIntoApplication(Client updatedClient) throws ClientUpdationException {
        if (updatedClient == null) throw new ClientUpdationException("Null Data Received, Please verify and Try Again");
        if (updatedClient.getName() == null) throw new ClientUpdationException("Client's Name Field Cannot Be Null, Please verify and Try Again");
        if (updatedClient.getDateOfBirth() == null) throw new ClientUpdationException("Client's Date of Birth Field Cannot Be Null, Please verify and Try Again");
        if (updatedClient.getAge() < 18) throw new ClientUpdationException("Age should be greater than 18");
        if (updatedClient.getMobileNumber()==null) throw new ClientUpdationException("Client's Mobile Number Field Cannot Be Negative, Please verify and Try Again");
        if (updatedClient.getEmail() == null) throw new ClientUpdationException("Client's Email Field Cannot Be Null, Please verify and Try Again");
        if (this.clientRepository.findByEmail(updatedClient.getEmail()).isEmpty()) throw new ClientUpdationException("Account With Given Email Does Not Exist, Please verify and Try Again");
        if (updatedClient.getPassword() == null) throw new ClientUpdationException("Client's Password Field Cannot Be Null, Please verify and Try Again");

        Optional<Client> clientOptional=this.clientRepository.findByEmail(updatedClient.getEmail());
        Client clientToBeUpdated=clientOptional.get();

        clientToBeUpdated.setName(updatedClient.getName());
        clientToBeUpdated.setDateOfBirth(updatedClient.getDateOfBirth());
        clientToBeUpdated.getAge();
        clientToBeUpdated.setMobileNumber(updatedClient.getMobileNumber());
        clientToBeUpdated.setPassword(updatedClient.getPassword());

        return this.clientRepository.save(clientToBeUpdated);
    }

    @Override
    public Client deleteClientAccountFromApplication(String email, String password) throws ClientDeletionException {
        if (email == null) throw new ClientDeletionException("Client's Email Field Cannot Be Null, Please verify and Try Again");
        if(password==null) throw new ClientDeletionException("Client's Password Field Cannot Be Null, Please verify and Try Again");
        Optional<Client> clientOptional=this.clientRepository.findByEmail(email);
        if(clientOptional.isEmpty()) throw new ClientDeletionException("Account With Given Email Does Not Exist, Please verify and Try Again");
        Client clientToBeDeleted=clientOptional.get();
        if(!clientToBeDeleted.getPassword().equals(password)) throw new ClientDeletionException("Incorrect Password, Please Try Again");

        //Should SET isActive to FALSE
        clientToBeDeleted.setIsActive(false);

        this.clientRepository.save(clientToBeDeleted);
        return clientToBeDeleted;
    }

    @Override
    public void deleteAllClients() {
        this.clientRepository.deleteAll();
    }

    @Override
    public List<Doctor> getAvailableDoctors() {
        return this.doctorRepository.findByIsActive(true);
    }

    @Override
    public List<Doctor> getAvailableDoctorsByName(String doctorName) throws ClientDoctorSearchingException{
        if (doctorName==null) throw new ClientDoctorSearchingException("Doctor's Name Cannot Be Null, Please Try Again");
        if (doctorName.isEmpty())  throw new ClientDoctorSearchingException("Doctor's Name Cannot Be Blank, Please Try Again");
        return this.doctorRepository.findByNameAndIsActive(doctorName, true);
    }
    @Override
    public List<Doctor> getAllAvailableDoctorsBySpecialization(String specialization) throws ClientDoctorSearchingException {
        if (specialization == null) throw new ClientDoctorSearchingException("Doctor's Specialization Cannot Be Null! Please Try Again!");
        if (specialization.isEmpty()) throw new ClientDoctorSearchingException("Doctor's Specialization Cannot Be Blank! Please Try Again!");
        return this.doctorRepository.findBySpecializationAndIsActive(specialization, true);
    }

    @Override
    public List<Doctor> getAllAvailableDoctorsSortedBy(String attribute) throws ClientDoctorSearchingException {
        if (attribute.equalsIgnoreCase("experience")) return this.getAvailableDoctors().stream().sorted(Comparator.comparing(Doctor::getExperience)).collect(Collectors.toList());
        else if (attribute.equalsIgnoreCase("consultancy-fee")) return this.getAvailableDoctors().stream().sorted(Comparator.comparing(Doctor::getConsultancyFee)).collect(Collectors.toList());
        else if(attribute.equalsIgnoreCase("name")) return this.getAvailableDoctors().stream().sorted(Comparator.comparing(Doctor::getName)).collect(Collectors.toList());
        else if(attribute.equalsIgnoreCase("specialization")) return this.getAvailableDoctors().stream().sorted(Comparator.comparing(Doctor::getSpecialization)).collect(Collectors.toList());
        else throw new ClientDoctorSearchingException("Search Attribute Invalid! Please Try Again!");
    }

    @Override
    public Appointment bookAppointmentInClientApplication(Appointment appointment , LocalDate bookingDate) throws ClientAppointmentBookingException {

        if (appointment.getClientID() == null) throw new ClientAppointmentBookingException("Client ID field cannot be null, Please try again!");
        if (this.clientRepository.findById(appointment.getClientID()).isEmpty()) throw new ClientAppointmentBookingException("Client ID not found in database, please retry again");
        if (appointment.getDoctorID() == null) throw new ClientAppointmentBookingException("Doctor ID field cannot be null, Please try again!");
        if (this.doctorRepository.findById(appointment.getDoctorID()).isEmpty()) throw new ClientAppointmentBookingException("Doctor ID not found in database, please retry again");
        if (appointment.getAppointmentDescription() == null) throw new ClientAppointmentBookingException("Appoint description cannot be null, please try again!");
        if (appointment.getPaymentStatus() == null) throw new ClientAppointmentBookingException("Payment Status field cannot be null");
        if (appointment.getDoctorConfirmationStatus() == null) throw new ClientAppointmentBookingException("Doctor Confirmation field cannot be null, please retry again!");
        if (appointment.getAppointmentDate() == null) throw new ClientAppointmentBookingException("Appointment Date field cannot be null, please retry again!");
//        if (appointment.getAppointmentDate().isBefore(bookingDate)) throw new ClientAppointmentBookingException("Cannot book to previous date, Please book for future dates");
        if (appointment.getAppointmentSlot() == null) throw new ClientAppointmentBookingException("Appointment Slot field cannot be null, please retry again!");


        // Try using number of slots as variable
        if (appointment.getAppointmentSlot() > 4 || appointment.getAppointmentSlot() <= 0) throw new ClientAppointmentBookingException("Appointment Slots must be in range of 1 to 4");
        if (this.appointmentRepository.findByDoctorIDAndAppointmentDateAndAndAppointmentSlot(appointment.getDoctorID(),appointment.getAppointmentDate(),appointment.getAppointmentSlot()).isPresent()) throw new ClientAppointmentBookingException("Slot Already booked, please try booking another slot");

        this.clientRepository.getReferenceById(appointment.getClientID()).getAppointmentList().add(appointment);
        this.doctorRepository.getReferenceById(appointment.getDoctorID()).getAppointmentList().add(appointment);

        return this.appointmentRepository.save(appointment);
    }

    @Override
    public Appointment makePaymentForAppointment(Integer appointmentID) throws ClientAppointmentPaymentException {
        if (appointmentID==null) throw new ClientAppointmentPaymentException("Appointment ID cannot be Null, please try again");
        Optional<Appointment> appointmentOptional=this.appointmentRepository.findAppointmentById(appointmentID);
        if (appointmentOptional.isEmpty() ) throw new ClientAppointmentPaymentException("Appointment for the given ID does not exist!, Please try again ");
        Appointment foundAppointment=appointmentOptional.get();
        if (foundAppointment.getPaymentStatus()) throw new ClientAppointmentPaymentException("Payment has already been made.");
        if(!foundAppointment.getDoctorConfirmationStatus()) throw new ClientAppointmentPaymentException("Appointment is yet to be approved by the Doctor, Please try again after sometime");
        foundAppointment.setPaymentStatus(true);
        this.appointmentRepository.save(foundAppointment);
        return foundAppointment;
    }
    @Override
    public List<Appointment> getAllAppointments(Integer clientID) throws ClientAppointmentsFetchingException {
        if (clientID == null) throw new ClientAppointmentsFetchingException("Client ID field cannot be null, Please retry again!");
        if (this.clientRepository.findById(clientID).isEmpty()) throw new ClientAppointmentsFetchingException("Client ID doesn't exist, please retry again");
        return this.appointmentRepository.findAppointmentByClientID(clientID);
    }

    @Override
    public List<Appointment> getAllPreviousAppointments(Integer clientID, LocalDate currentDate) throws ClientAppointmentsFetchingException {
        if (clientID == null) throw new ClientAppointmentsFetchingException("Client ID field cannot be null, Please retry again!");
        if (this.clientRepository.findById(clientID).isEmpty()) throw new ClientAppointmentsFetchingException("Client ID doesn't exist, please retry again");
        if (currentDate == null) throw new ClientAppointmentsFetchingException("Current Date slot cannot be null, Please retry again");
        return this.appointmentRepository.findAppointmentByClientID(clientID).stream().filter(appointment -> appointment.getAppointmentDate().isBefore(currentDate)).collect(Collectors.toList());
    }
    @Override
    public List<Appointment> getAllFutureAppointments(Integer clientID, LocalDate currentDate) throws ClientAppointmentsFetchingException {
        if (clientID == null) throw new ClientAppointmentsFetchingException("Client ID field cannot be null, Please retry again!");
        if (this.clientRepository.findById(clientID).isEmpty()) throw new ClientAppointmentsFetchingException("Client ID doesn't exist, please retry again");
        if (currentDate == null) throw new ClientAppointmentsFetchingException("Current Date slot cannot be null, Please retry again");
        return this.appointmentRepository.findAppointmentByClientID(clientID).stream().filter(appointment -> appointment.getAppointmentDate().isAfter(currentDate)).collect(Collectors.toList());
    }

    @Override
    public void deleteAllAppointments() {
        this.appointmentRepository.deleteAll();
    }


}
