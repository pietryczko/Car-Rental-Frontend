package com.CarRental.Frontend.views.rent;

import com.CarRental.Frontend.Exceptions.DataNotValidate;
import com.CarRental.Frontend.domain.Car;
import com.CarRental.Frontend.domain.City;
import com.CarRental.Frontend.domain.Rent;
import com.CarRental.Frontend.domain.status.CarStatus;
import com.CarRental.Frontend.services.CarService;
import com.CarRental.Frontend.services.CityService;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RentForm extends HorizontalLayout {
    private final ComboBox<Car> car = new ComboBox<>("Choose car:");
    private final ComboBox<City> city = new ComboBox<>("Choose city:");
    private final RentProcessor rentProcessor;
    private final CarService carService = CarService.getInstance();
    private final CityService cityService = CityService.getInstance();


    public RentForm() {
        DatePicker starDate = new DatePicker("Start of rent:");
        DatePicker endDate = new DatePicker("End of rent:");

        rentProcessor = new RentProcessor(car, city, starDate, endDate);

        updateCars();
        updateCities();

        add(car, city, starDate, endDate);
    }

    private void updateCars() {
        List<Car> availableCars = new ArrayList<>();

        for (Car car : carService.getCars()) {
            if (car.getCarStatus() == CarStatus.AVAILABLE) {
                availableCars.add(car);
            }
        }

        car.setItems(availableCars);
        car.setItemLabelGenerator((ItemLabelGenerator<Car>) item ->
                item.getBrand() + " " + item.getModel()
                        + " Cost: " + costSymbol(item.getPrice().intValue()));
    }

    private void updateCities() {
        city.setItems(cityService.getCities());
        city.setItemLabelGenerator((ItemLabelGenerator<City>) City::getName);
    }

    private String costSymbol(int price) {
        if (price < 100) {
            return "Lowest";
        } else if (price < 200) {
            return "Medium";
        } else {
            return "High";
        }
    }

    public BigDecimal getPrice() throws DataNotValidate {
        return rentProcessor.calculatePrice();
    }

    public Rent createRent() throws DataNotValidate {
        return rentProcessor.createRent();
    }
}
