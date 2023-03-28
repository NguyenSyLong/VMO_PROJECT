package com.apartment.vmoproject.api.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "apartment")
public class Apartment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Name cannot be null")
//    @NotEmpty(message = "Name cannot be empty")
    private String name;

//    @NotNull(message = "Area cannot be null")
//    @Min(value = 0, message = "Area must be positive")
    private Float area;

//    @NotNull(message = "Price cannot be null")
//    @Min(value = 0, message = "Price must be positive")
    private Long price;

//    @NotNull(message = "Description cannot be null")
//    @NotEmpty(message = "Description cannot be empty")
    private String description;

//    @NotNull(message = "The number of room cannot be null")
//    @Min(value = 0, message = "The number of room be positive")
    private Integer numOfRoom;

//    @NotNull(message = "The number of dweller cannot be null")
//    @Min(value = 0, message = "The number of dweller must be positive")
    private Integer numOfDweller;

//    @NotNull(message = "Status cannot be null")
    private Boolean status;


    @OneToMany(mappedBy = "apartment",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Dweller> dwellers = new ArrayList<>();

    @OneToMany(mappedBy = "apartment",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Bill> bills = new ArrayList<>();

}
