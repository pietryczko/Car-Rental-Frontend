package com.CarRental.Frontend.views;

import com.CarRental.Frontend.services.CarService;
import com.CarRental.Frontend.views.rent.RentForm;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private CarService carService;
    private HorizontalLayout mainLayout = new HorizontalLayout();
    private RentForm form;

    public MainView() {
        form = new RentForm();
        addClassName("main-view");
        setHeader();
        add(mainLayout, form);
        setSizeFull();
    }

    private void setHeader() {
        mainLayout.add(new Paragraph("Welcome to Car Rental site!"));
        mainLayout.setSizeFull();
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.add(getLoginButtons());
    }


    private HorizontalLayout getLoginButtons() {
        Anchor logIn = new Anchor("login", "Log In");
        Anchor register = new Anchor("register", "Register");
        HorizontalLayout layout = new HorizontalLayout(logIn, register);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.END);
        return layout;
    }
//
//
//    private HorizontalLayout getRentLayout() {
//        Button rentButton = new Button("Rent car", e -> Notification.show("Car Rented!"));
//        DatePicker starDate = new DatePicker("Start of rent:");
//        DatePicker endDate = new DatePicker("End of rent:");
//
//        Button calculateRent = new Button("Calculate price");
//        HorizontalLayout layout = new HorizontalLayout(getCarBox(), getCites(), starDate, endDate, rentButton, calculateRent);
//        layout.setSizeFull();
//        layout.setDefaultVerticalComponentAlignment(END);
//        layout.setJustifyContentMode(JustifyContentMode.CENTER);
//
//
//        calculateRent.addClickListener(click -> {
//            Button newButton = new Button();
//            add(newButton);
//            Notification.show(("Price is " + Period.between(starDate.getValue(), endDate.getValue()).getDays()));
//        });
//        return layout;
//    }
//
//    private ComboBox getCarBox() {
//        ComboBox<Car> comboBox = new ComboBox<>("Choose car:");
//        List<Car> cars = new ArrayList<>();
//
//        for(Car car : carService.getCars()) {
//            if (car.getCarStatus() == CarStatus.AVAILABLE) {
//                cars.add(car);
//            }
//        }
//        comboBox.setItems(cars);
//        comboBox.setItemLabelGenerator(new ItemLabelGenerator<Car>() {
//            @Override
//            public String apply(Car item) {
//                return item.getBrand() + " " + item.getModel();
//            }
//        });
//        return comboBox;
//    }
//
//    private ComboBox getCites() {
//        ComboBox<String> comboBox = new ComboBox<>("Choose city");
//        comboBox.setItems(List.of("Poznań", "Warszawa", "Wrocław", "Katowice", "Gdańsk", "Katowice"));
//        return comboBox;
//    }
}

