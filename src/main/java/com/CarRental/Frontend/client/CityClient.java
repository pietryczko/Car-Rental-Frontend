package com.CarRental.Frontend.client;

import com.CarRental.Frontend.domain.City;
import com.vaadin.flow.component.html.Paragraph;
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
public class CityClient {

    private final RestTemplate restTemplate;
    private static  final String CITY_URL = MainUrl.URL + "city";
    private URI uri = UriComponentsBuilder.fromUriString(CITY_URL).build().toUri();
    private  static CityClient cityClient;

    public static CityClient getInstance() {
        if (cityClient == null) {
            cityClient = new CityClient(new RestTemplate());
        }
        return cityClient;
    }

    public List<City> getCities() {
        try {
            City[] cities = restTemplate.getForObject(uri, City[].class);
            return Arrays.asList(Objects.requireNonNull(cities));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void createCity(City city) {
        try {
            restTemplate.postForObject(uri, city, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void removeCity(City city) {
        try {
            restTemplate.delete(UriComponentsBuilder.fromUriString(uri + "/" + city.getId())
                    .build().toUri());
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
