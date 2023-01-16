package com.SKP2.ClientApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarCreateDto {
    private Long modelId;
    private Long typeId;
    private Long companyId;
    private Integer rentalDayPrice;
}
