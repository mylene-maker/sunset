package com.humanbooster.sunset.services;

import com.humanbooster.sunset.forms.Email;
import com.humanbooster.sunset.models.Reservation;
import com.humanbooster.sunset.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private ReservationRepository reservationRepository;

    public void sendEmail(Email email, Long reservationId){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("msaidousseni@stagiaire-humanbooster.com");
        message.setTo(email.getEmail());
        message.setSubject(email.getObject());
        message.setText(email.getContenu());

        javaMailSender.send(message);

        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservationRepository.delete(reservation);
        }

    }


}
