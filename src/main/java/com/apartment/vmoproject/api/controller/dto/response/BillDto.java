package com.apartment.vmoproject.api.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class BillDto {

    private String fromDate;

    private String toDate;

    private String dateOfPayment;

    private Long apartmentId;

    private List<Long> serviceId;




}
