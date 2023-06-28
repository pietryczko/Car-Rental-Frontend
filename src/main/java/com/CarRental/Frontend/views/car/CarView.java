package com.CarRental.Frontend.views.car;

import com.CarRental.Frontend.domain.dto.CarDto;
import com.CarRental.Frontend.services.CarService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "car")
@PageTitle("Car Panel")
public class CarView extends VerticalLayout {
    Grid<CarDto> grid = new Grid<>(CarDto.class);
    CarService carService = CarService.getInstance();
    CarForm carForm;

    public CarView() {
        carForm = new CarForm();
        addClassName("car-view");
        setSizeFull();
        add(carForm,grid);
        updateList();
    }

    private void updateList() {
        grid.setItems(carService.getCars());
    }

}

