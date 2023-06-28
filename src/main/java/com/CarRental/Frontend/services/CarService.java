package com.CarRental.Frontend.services;

import com.CarRental.Frontend.client.CarClient;
import com.CarRental.Frontend.domain.dto.CarDto;
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

    public void save(CarDto carDto) {
        carClient.createCar(carDto);
    }

    public List<CarDto> getCars() {
        return carClient.getCars();
    }
}
