package com.CarRental.Frontend.views.car;


import com.CarRental.Frontend.domain.dto.CarDto;
import com.CarRental.Frontend.domain.dto.CarStatus;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;


public class CarForm extends FormLayout {

    TextField brand = new TextField("Car Brand");
    TextField model = new TextField("Car Model");
    TextField licencePlateNumber = new TextField("Licence Plate Number");
    ComboBox<CarStatus> carStatus = new ComboBox("Status");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");

    Binder<CarDto> binder = new BeanValidationBinder<>(CarDto.class);

    public CarForm() {
        addClassName("car-form");
        carStatus.setItems(CarStatus.values());
        binder.bindInstanceFields(this);
        add(brand,
            model,
            licencePlateNumber,
            carStatus,
            createButtonLayout());
    }

    public void setCar(CarDto carDto) {
        binder.setBean(carDto);
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(event -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Events
    public static abstract class CarFormEvent extends ComponentEvent<CarForm> {
        private CarDto carDto;

        protected CarFormEvent(CarForm source, CarDto carDto) {
            super(source, false);
            this.carDto = carDto;
        }

        public CarDto getCarDto() {
            return carDto;
        }
    }

    public static class SaveEvent extends CarFormEvent {
        SaveEvent(CarForm source, CarDto carDto) {
            super(source, carDto);
        }
    }

    public static class DeleteEvent extends CarFormEvent {
        DeleteEvent(CarForm source, CarDto carDto) {
            super(source, carDto);
        }

    }

    public static class CloseEvent extends CarFormEvent {
        CloseEvent(CarForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
