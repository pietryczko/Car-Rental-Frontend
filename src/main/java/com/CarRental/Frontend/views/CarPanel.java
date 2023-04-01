package com.CarRental.Frontend.views;

import com.CarRental.Frontend.domain.dto.CarDto;
import com.CarRental.Frontend.services.CarService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "car")
public class CarPanel extends VerticalLayout {
    Grid<CarDto> grid = new Grid<>(CarDto.class);
    private CarService carService;
    public CarPanel(CarService carService) {
        this.carService = carService;
    addClassName("car-view");
    setSizeFull();
    configureGrid();
    add(grid);
    updateList();
    }

    private void configureGrid() {
        grid.addClassName("car-grid");
        grid.setSizeFull();
        grid.setColumns("id", "brand", "model", "licencePlateNumber", "carStatus", "rentsId");
    }

    private void updateList() {
        grid.setItems(carService.getCars());
    }
}

