package com.CarRental.Frontend.services;

import com.CarRental.Frontend.client.CityClient;
import com.CarRental.Frontend.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityClient client;
    private static CityService service;
    public static CityService getInstance() {
        if (service == null) {
            service = new CityService(CityClient.getInstance());
        }
        return service;
    }

    public void save(City city) {client.createCity(city);}
    public void delete(City city) {client.removeCity(city);}
    public List<City> getCities() {return client.getCities();}
}
