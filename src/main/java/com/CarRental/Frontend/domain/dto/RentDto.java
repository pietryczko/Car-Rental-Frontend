package com.CarRental.Frontend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentDto {
    private long id;
    private RentStatus rentStatus;
    private BigDecimal cost;
    private LocalDate startRentDate;
    private LocalDate endRentDate;
    private Long carId;
    private Long userId;
}
