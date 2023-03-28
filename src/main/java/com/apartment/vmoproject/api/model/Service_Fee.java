package com.apartment.vmoproject.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "service_fee")
public class Service_Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private Float unitPrice;

    @OneToMany(mappedBy = "service_fee",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Bill_Detail> bill_details = new ArrayList<>();


}
