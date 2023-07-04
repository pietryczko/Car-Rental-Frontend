package com.CarRental.Frontend.views.rent;

import com.CarRental.Frontend.Exceptions.DataNotValidate;
import com.CarRental.Frontend.domain.Car;
import com.CarRental.Frontend.domain.City;
import com.CarRental.Frontend.domain.Rent;
import com.CarRental.Frontend.domain.status.RentStatus;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentProcessor {

    private final ComboBox<Car> car;
    private final ComboBox<City> city;
    private final DatePicker starDate;
    private final DatePicker endDate;

    RentProcessor(ComboBox<Car> car, ComboBox<City> city, DatePicker starDate, DatePicker endDate) {
        this.car = car;
        this.city = city;
        this.starDate = starDate;
        this.endDate = endDate;
    }


    public boolean validate() throws DataNotValidate {
        if (!car.isEmpty() &&
                !city.isEmpty() &&
                !starDate.isEmpty() &&
                !endDate.isEmpty()) {
            return true;
        } else {
            throw new DataNotValidate();
        }
    }

    private long calculateDays() throws DataNotValidate {
        long days = 0;
        if (validate()) {
            LocalDate data1 = starDate.getValue();
            LocalDate data2 = endDate.getValue();
            if (!(data1 == null) && !(data2 == null)) {
                days = ChronoUnit.DAYS.between(data1, data2);
            }
            if (!(days > 0)) {
                throw new DataNotValidate("Set the correct rental date.");
            }
        } else {
            throw new DataNotValidate();
        }
        return days;
    }

    public BigDecimal calculatePrice() throws DataNotValidate {
        BigDecimal result;
        if (validate()) {
            BigDecimal cityMultiply = city.getValue().getPriceMultiplier();
            BigDecimal carCost = car.getValue().getPrice();
            BigDecimal days = new BigDecimal(calculateDays());
            result = days.multiply(carCost).multiply(cityMultiply);
        } else {
            throw new DataNotValidate();
        }
        return result.setScale(2, RoundingMode.UP);
    }

    public Rent createRent() throws DataNotValidate {
        Rent newRent;
        if (validate()) {
            newRent = Rent.builder()
                    .rentStatus(RentStatus.BOOKED)
                    .startRentDate(starDate.getValue())
                    .endRentDate(endDate.getValue())
                    .cost(calculatePrice())
                    .carId(car.getValue().getId())
                    .userId(null)
                    .build();
        } else {
            throw new DataNotValidate();
        }
        return newRent;
    }
}
