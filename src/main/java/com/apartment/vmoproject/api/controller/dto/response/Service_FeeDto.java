package com.apartment.vmoproject.api.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Service_FeeDto {
    private String name;
    private Float unitPrice;
}
