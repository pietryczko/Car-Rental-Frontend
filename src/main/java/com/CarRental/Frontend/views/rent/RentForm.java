package com.CarRental.Frontend.views.rent;

import com.CarRental.Frontend.domain.Car;
import com.CarRental.Frontend.domain.City;
import com.CarRental.Frontend.domain.Rent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

import java.time.Period;

public class RentForm extends FormLayout {
    private ComboBox<Car> car = new ComboBox("Choose car:");
    private ComboBox<City> city = new ComboBox("Choose city:");
    private DatePicker starDate = new DatePicker("Start of rent:");
    private DatePicker endDate = new DatePicker("End of rent:");

    Button price = new Button("Show price");

    Binder<Rent> binder = new BeanValidationBinder<>(Rent.class);

    public RentForm() {
        addClassName("rent-form");
        add(createLayout(), price);
        price.addClickListener(event -> {
            add("PRICE:" +
                    (Period.between(starDate.getValue(),
                            endDate.getValue()).getDays()) * 10 + " $");
        });

    }

    private Component createLayout() {
        price.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        price.addClickShortcut(Key.ENTER);
        return new HorizontalLayout(car, city, starDate, endDate, price);
    }


}
