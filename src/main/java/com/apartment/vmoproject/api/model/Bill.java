package com.apartment.vmoproject.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;


    private Date toDate;

    private Date dateOfPayment;

    private Long totalPrice;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    @JsonBackReference
    private Apartment apartment;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Bill_Detail> bill_details = new ArrayList<>();

}
