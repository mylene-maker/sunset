package com.humanbooster.sunset.services;

import com.humanbooster.sunset.forms.EmplacementForm;
import com.humanbooster.sunset.forms.ReservationForm;
import com.humanbooster.sunset.models.Command;
import com.humanbooster.sunset.models.Reservation;
import com.humanbooster.sunset.models.User;
import com.humanbooster.sunset.repositories.CommandRepository;
import com.humanbooster.sunset.repositories.ReservationRepository;
import com.humanbooster.sunset.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    private User user;

    public List<Reservation> findAll() {
        return (List<Reservation>) this.reservationRepository.findAll();
    }

    public List<EmplacementForm> payloadToResas(String requestBody) {

        List<EmplacementForm> reservationForm = new ArrayList<>();

        // On sépare chacun de nos éléments en faisant un split sur le &
        // On met tout ça en list pour simplifier l'utilisation
        String[] params = requestBody.split("&");
        List<String> listParam = Arrays.stream(params).toList();

        // Je cré un Hashmap (clé => valeur) cela nous permettra de rechercher facilement
        // des éléments de notre requêtes en fonction du champs souhaité
        Map<String, String> mapParam = new HashMap<>();

        // Je par cours ma liste de paramètre
        // Un paramètre sera donc par exemple items_3_equipement=lit
        for (String paramString : listParam) {
            // Je fais un split sur le "=" pour séparer la clé de la valeur
            String[] strParam = paramString.split("=");

            List<String> paramList = Arrays.stream(strParam).toList();
            if (paramList.size() == 2) {
                // J'ajoute dans mon hashmap la clé et la valeur
                mapParam.put(paramList.get(0), paramList.get(1));
            }
        }

        // Je parcours la map que j'ai créé précédement
        for (Map.Entry<String, String> entry : mapParam.entrySet()) {

            // Si la clé de ma map commence par items_ et fini par _file
            // Je vais devoir créer un nouvel emplacement
            if (entry.getKey().startsWith("items_") && entry.getKey().endsWith("_file")) {
                // Je réccupére l'index de mon emplacement
                // Ceci me permettra de retrouver les autres champs en relation avec mon emplacement
                String[] splitItemName = entry.getKey().split("_");
                List<String> splitNameList = Arrays.stream(splitItemName).toList();
                String index = splitNameList.get(1);

                // Je cré mon emplacement
                EmplacementForm emplacementForm = new EmplacementForm();
                // Je récupére mon la file dans hmap qui représente ma requête
                emplacementForm.setFile(Integer.valueOf(mapParam.get("items_" + index + "_file")));
                // Je réccupére mon equipement dans le hashmap qui représente ma requête
                emplacementForm.setEquipment(mapParam.get("items_" + index + "_equipment"));

                // J'ajoute mon emplacement dans mon formulaire
                reservationForm.add(emplacementForm);
            }

        }
        // Je retourne ma liste d'emplacement
        return reservationForm;
    }

    public void persistReservationFromForm(ReservationForm reservationForm, String email) {
        Command command = new Command();
        command.setUser(this.userRepository.findByEmail(email));
        command.setRemarque(reservationForm.getRemarque());
        this.commandRepository.save(command);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calArrivee = Calendar.getInstance();
        Calendar calSortie = Calendar.getInstance();

        for (EmplacementForm emplacementForm : reservationForm.getEmplacements()) {
            calArrivee.setTime(reservationForm.getDateStart());
            calSortie.setTime(reservationForm.getDateEnd());
            String stringDateArrivee = sdf.format(calArrivee.getTime());
            String stringDateDepart = sdf.format(calSortie.getTime());

            if (stringDateArrivee.equals(stringDateDepart)) {

                Reservation reservation = new Reservation();
                reservation.setDate_to(calArrivee.getTime());
                reservation.setLane(emplacementForm.getFile());
                reservation.setEquipment(emplacementForm.getEquipment());
                reservation.setCommand(command);

                this.reservationRepository.save(reservation);

            } else {
                Reservation reservation = new Reservation();
                reservation.setDate_to(calArrivee.getTime());
                reservation.setLane(emplacementForm.getFile());
                reservation.setEquipment(emplacementForm.getEquipment());
                reservation.setCommand(command);

                this.reservationRepository.save(reservation);

                while (!stringDateArrivee.equals(stringDateDepart)) {

                    calArrivee.add(Calendar.DAY_OF_MONTH, 1);
                    stringDateArrivee = sdf.format(calArrivee.getTime());
                    reservation = new Reservation();
                    reservation.setDate_to(calArrivee.getTime());
                    reservation.setLane(emplacementForm.getFile());
                    reservation.setEquipment(emplacementForm.getEquipment());
                    reservation.setCommand(command);

                    this.reservationRepository.save(reservation);
                }
            }

        }

    }
    public void acceptReservation(Long reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setAccepted(true);
            reservationRepository.save(reservation);
        }
    }

    public void rejectReservation(Long reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

//            reservationRepository.delete(reservation);
        }
    }

    public void editReservation(Long reservationId, int column) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setColumn(column);
            reservationRepository.save(reservation);
        }
    }

}
