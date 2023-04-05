package com.CarRental.Frontend.views.car;

import com.CarRental.Frontend.domain.dto.CarStatus;
import com.CarRental.Frontend.domain.dto.RentDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CarForm extends FormLayout {

    TextField brand = new TextField("Brand");
    TextField model = new TextField("Model");
    TextField licencePlateNumber = new TextField("Licence Plate Num");
    ComboBox<CarStatus> status = new ComboBox<>("Car Status");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public CarForm() {
        addClassName("car-form");

        add(
                brand,
                model,
                licencePlateNumber,
                status,
                createButtonsLayout()
        );
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }
}
