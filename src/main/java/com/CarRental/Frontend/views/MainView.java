package com.CarRental.Frontend.views;

import com.CarRental.Frontend.Exceptions.DataNotValidate;
import com.CarRental.Frontend.domain.Rent;
import com.CarRental.Frontend.views.rent.RentForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private final RentForm form;
    private final Button rentCar = new Button("Rent car");
    private final Paragraph cost = new Paragraph("COST TEST");
    private final Paragraph rent = new Paragraph("RENT");

    public MainView() {
        addClassName("main-view");
        form = new RentForm();

        setHorizontalComponentAlignment(Alignment.CENTER, form);

        rent.setVisible(false);
        rentCar.setVisible(false);
        cost.setVisible(false);

        setHorizontalComponentAlignment(Alignment.CENTER, cost);

        Button showPrice = new Button("Show Price");

        add(getLoginButtons(), setHeader(), form, showPrice, cost, rentCar, rent);

        showPrice.addClickListener(event -> {
            try {
                cost.setText(form.getPrice() + "$");
                cost.setVisible(true);
                rentCar.setVisible(true);
            } catch (DataNotValidate e) {
                cost.setVisible(true);
                cost.setText(e.getMessage());
            }
        });

        rentCar.addClickListener(event -> {
            try {
                Rent newRent = form.createRent();
                rent.setText("You rent a car! Your rent id: " + newRent.hashCode());
                rent.setVisible(true);
            } catch (DataNotValidate e) {
                rent.setText(e.getMessage());
                rent.setVisible(true);
            }
        });

        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }

    private HorizontalLayout setHeader() {
        Paragraph welcomeMess = new Paragraph("Welcome to Car Rental site!");
        setSizeFull();
        HorizontalLayout layout = new HorizontalLayout(welcomeMess);
        layout.setVerticalComponentAlignment(Alignment.CENTER, layout);
        return layout;
    }


    private HorizontalLayout getLoginButtons() {
        Anchor logIn = new Anchor("login", "Log In");
        Anchor register = new Anchor("register", "Register");
        setSizeFull();
        HorizontalLayout layout = new HorizontalLayout(logIn, register);
        layout.setVerticalComponentAlignment(Alignment.END, layout);
        return layout;
    }
}

