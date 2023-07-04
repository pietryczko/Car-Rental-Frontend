package com.CarRental.Frontend.Exceptions;

public class DataNotValidate extends Exception {
    public DataNotValidate() {
        super("Complete all missing data in the fields.");
    }

    public DataNotValidate(String message) {
        super(message);
    }
}
