package com.apartment.vmoproject.api.controller.dto.response;

import com.apartment.vmoproject.api.model.Apartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DwellerDto {


    private String name;

    private String email;

    private String frontSideImage;

    private String backSideImage;

    private Date dateOfBirth;

    private Boolean gender;

    private Boolean status;

    private Apartment apartment;
}




