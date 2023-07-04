package com.CarRental.Frontend.domain;

import com.CarRental.Frontend.domain.status.CarStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private long id;
    private String licencePlateNumber;
    private String brand;
    private String model;
    private CarStatus carStatus;
    private BigDecimal price;
    private List<Long> rentsId;
}
