package com.CarRental.Frontend.views.car;

import com.CarRental.Frontend.domain.Car;
import com.CarRental.Frontend.services.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "car")
@PageTitle("Car Panel")
public class CarView extends VerticalLayout {
    private Grid<Car> grid = new Grid<>(Car.class);
    private CarService carService = CarService.getInstance();
    private CarForm carForm;

    public CarView() {
        Anchor back = new Anchor("admin", "Back");
        carForm = new CarForm();
        addClassName("car-view");
        grid.setColumns("id", "brand", "model", "licencePlateNumber", "price", "carStatus", "rentsId");
        setSizeFull();
        updateList();
        Button addCar = new Button("Add Car", event -> addContact());

        add(addCar,carForm,grid,back);

        carForm.addSaveListener(this::saveCar);
        carForm.addDeleteListener(this::deleteCar);
        carForm.addCloseListener(event -> closeEditor());


        closeEditor();
        grid.asSingleSelect().addValueChangeListener(event -> editCar(event.getValue()));
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editCar(new Car());
    }

    private void editCar(Car car) {
        if (car == null) {
            closeEditor();
        } else {
        carForm.setCar(car);
        carForm.setVisible(true);
        addClassName("editing");
        }
    }

    private void saveCar(CarForm.SaveEvent saveEvent) {
        carService.save(saveEvent.getCarDto());
        updateList();
        closeEditor();
    }

    private void deleteCar(CarForm.DeleteEvent deleteEvent) {
        updateList();
        carService.delete(deleteEvent.getCarDto());
        closeEditor();
    }

    private void closeEditor() {
        carForm.setCar(null);
        carForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(carService.getCars());
    }
}