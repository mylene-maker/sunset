package com.humanbooster.sunset;

import com.humanbooster.sunset.exception.NegativeDureeException;
import com.humanbooster.sunset.models.Command;
import com.humanbooster.sunset.models.Reservation;
import com.humanbooster.sunset.utils.ReservationUtils;
import com.humanbooster.sunset.exception.TypeNotSupportedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculateTotalPrice {

    private ReservationUtils calculatePrice;

    @BeforeAll
    public void init(){ calculatePrice = new ReservationUtils(); }

    // test for a command with multiple location in the same lane
    @Test
    public void calculateTotalPriceWithDifferentLocationInOneLane() throws ParseException, TypeNotSupportedException {

        //Create command
        Command commandTest = new Command();

        String dateString = "2024-07-19";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateString);

        // create reservation in the first lane
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1, 1, date, false, "UN_LIT", commandTest));
        reservations.add(new Reservation(1, 2, date, false, "UN_LIT", commandTest));
        reservations.add(new Reservation(1, 3, date, false, "UN_LIT", commandTest));
        reservations.add(new Reservation(1, 4, date, false, "UN_LIT", commandTest));

        BigDecimal expectedPrice = BigDecimal.valueOf(260);
        BigDecimal price = BigDecimal.valueOf(0);

        price = ReservationUtils.calculateTotalPrice(reservations);

        Assertions.assertEquals(0, expectedPrice.compareTo(price));

    }

    // test for a command with different location in different lane
    @Test
    public void calculateTotalPriceWithDifferentLocationInMultipleLane() throws ParseException, TypeNotSupportedException {

        //Create command
        Command commandTest = new Command();

        String dateString = "2024-07-20";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateString);

        // create reservation in different lane
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1, 1, date, false, "UN_LIT", commandTest)); // Lane 1
        reservations.add(new Reservation(3, 2, date, false, "UN_LIT", commandTest)); // Lane 3
        reservations.add(new Reservation(6, 3, date, false, "UN_LIT", commandTest)); // Lane 6
        reservations.add(new Reservation(8, 4, date, false, "UN_LIT", commandTest)); // Lane 8

        BigDecimal expectedPrice = BigDecimal.valueOf(190);
        BigDecimal price = BigDecimal.valueOf(0);

        price = ReservationUtils.calculateTotalPrice(reservations);

        Assertions.assertEquals(0, expectedPrice.compareTo(price));

    }

}
