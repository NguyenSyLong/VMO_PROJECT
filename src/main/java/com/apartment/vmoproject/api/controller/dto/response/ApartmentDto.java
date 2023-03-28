package com.apartment.vmoproject.api.controller.dto.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ApartmentDto {

    private String name;

    private Float area;

    private Long price;

    private String description;

    private Integer numOfRoom;

    private Integer numOfDweller;

    private Boolean status;
}
