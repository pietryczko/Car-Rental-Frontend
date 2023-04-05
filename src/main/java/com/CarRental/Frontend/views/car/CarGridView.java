package com.CarRental.Frontend.views.car;

import com.CarRental.Frontend.domain.dto.CarDto;
import com.CarRental.Frontend.services.CarService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "car")
@PageTitle("Car Panel")
public class CarGridView extends VerticalLayout {

    private final CarForm form;
    Grid<CarDto> grid = new Grid<>(CarDto.class);
    private CarService carService;

    public CarGridView(CarService carService) {
        this.carService = carService;
    addClassName("car-view");
    setSizeFull();
    configureGrid();

    form = new CarForm();

    Div content = new Div(form, grid);
    content.addClassName("content");

    add(content);
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

