package com.CarRental.Frontend.client;

import com.CarRental.Frontend.domain.dto.CarDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarClient {

    private final RestTemplate restTemplate;

    private static final String CAR_URL = MainUrl.URL + "car";
    private static CarClient carClient;

    public static CarClient getInstance() {
        if (carClient == null) {
            carClient = new CarClient(new RestTemplate());
        }
        return carClient;
    }

    public List<CarDto> getCars(){
        URI uri = UriComponentsBuilder.fromUriString(CAR_URL).build().toUri();
        try{
            CarDto[] cars = restTemplate.getForObject(uri, CarDto[].class);
            return Arrays.asList(Objects.requireNonNull(cars));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CarDto getCar(Long carId) {
        URI uri = UriComponentsBuilder.fromUriString((CAR_URL + "/" + carId)).build().toUri();

        try {
            CarDto car = restTemplate.getForObject(uri, CarDto.class);
            return (Objects.requireNonNull(car));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
