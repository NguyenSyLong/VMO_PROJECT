package com.apartment.vmoproject.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "dweller")
public class Dweller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Name cannot be null")
//    @NotEmpty(message = "Name cannot be empty")
    private String name;

//    @Email(message = "Email không hợp lệ")
    private String email;

    private String frontSideImage;

    private String backSideImage;

    private Date dateOfBirth;

    private Boolean gender;

    private Boolean status;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    @JsonBackReference
    private Apartment apartment;




}
