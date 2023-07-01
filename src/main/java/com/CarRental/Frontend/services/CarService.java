package com.CarRental.Frontend.services;

import com.CarRental.Frontend.client.CarClient;
import com.CarRental.Frontend.domain.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarClient carClient;

    private static CarService carService;

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(CarClient.getInstance());
        }
        return carService;
    }

    public void save(Car car) {
        carClient.createCar(car);
    }

    public void delete(Car car) {
        carClient.remove(car);
    }

    public List<Car> getCars() {
        return carClient.getCars();
    }
}
