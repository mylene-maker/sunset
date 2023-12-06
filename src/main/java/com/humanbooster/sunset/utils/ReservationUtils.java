package com.humanbooster.sunset.utils;

import com.humanbooster.sunset.models.Reservation;

import java.math.BigDecimal;
import java.util.List;

public class ReservationUtils {

        public static boolean hasUnacceptedReservations(List<Reservation> reservations) {
            return reservations.stream().anyMatch(reservation -> !reservation.isAccepted());
        }

        public static boolean hasAcceptedReservations(List<Reservation> reservations) {
            return reservations.stream().anyMatch(Reservation::isAccepted);
        }

    public static BigDecimal calculateTotalPrice(List<Reservation> reservations) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            BigDecimal reservationPrice = getPriceByLane(reservation.getLane());
            totalPrice = totalPrice.add(reservationPrice);
        }

        return totalPrice;
    }
    public static BigDecimal getPriceByLane(int lane) {
        return switch (lane) {
            case 1 -> BigDecimal.valueOf(65.00);
            case 2 -> BigDecimal.valueOf(60.00);
            case 3 -> BigDecimal.valueOf(55.00);
            case 4 -> BigDecimal.valueOf(50.00);
            case 5 -> BigDecimal.valueOf(45.00);
            case 6 -> BigDecimal.valueOf(40.00);
            case 7 -> BigDecimal.valueOf(35.00);
            case 8 -> BigDecimal.valueOf(30.00);
            default -> BigDecimal.ZERO;
        };
    }
}
