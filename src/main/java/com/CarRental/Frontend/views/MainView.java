package com.CarRental.Frontend.views;

import com.CarRental.Frontend.client.CarClient;
import com.CarRental.Frontend.domain.dto.CarDto;
import com.CarRental.Frontend.services.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.*;

@Route
public class MainView extends VerticalLayout {

    private CarService carService;

    public MainView(CarService carService) {
        this.carService = carService;
        add(getLoginButtons());
    Paragraph text = new Paragraph("Welcome to Car Rental site!");
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    horizontalLayout.add(text);
    horizontalLayout.setSizeFull();
    horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    
        add(horizontalLayout);
        add(getRentLayout());
    }

    private HorizontalLayout getLoginButtons() {
        Anchor logIn = new Anchor("login","Log In");
        Anchor register = new Anchor("register", "Register");
        HorizontalLayout layout = new HorizontalLayout(logIn, register);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.END);
        return layout;
    }


    private HorizontalLayout getRentLayout() {
        Button rentButton = new Button("Rent car", e -> Notification.show("Car Rented!"));
        DatePicker starDate = new DatePicker("Start of rent:");
        DatePicker endDate = new DatePicker("End of rent:");
        HorizontalLayout layout = new HorizontalLayout(getCarBox(), getCites(), starDate, endDate, rentButton);
        layout.setSizeFull();
        layout.setDefaultVerticalComponentAlignment(END);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        return layout;
    }

    private ComboBox getCarBox() {
        ComboBox<CarDto> comboBox = new ComboBox<>("Choose car:");
        comboBox.setItems(carService.getCars());
        return comboBox;
    }

    private ComboBox getCites() {
        ComboBox<String> comboBox = new ComboBox<>("Choose city");
        comboBox.setItems(List.of("Poznań", "Warszawa", "Wrocław", "Katowice", "Gdańsk", "Katowice"));
        return comboBox;
    }
}

