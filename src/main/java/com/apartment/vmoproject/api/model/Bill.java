package com.apartment.vmoproject.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    @Column
    private Date dateOfPayment;

    private Long totalPrice;

    private Long waterConsumption;

    private Long electricConsumption;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "apartment_id")
    @JsonIgnoreProperties("bills")
    private Apartment apartment;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("bill")
    private List<Bill_Detail> bill_details = new ArrayList<>();

    public void removeBD(Bill_Detail bd)
    {
        this.bill_details.remove(bd);

    }
    public void addBillDetail(Bill_Detail bd) {
        this.bill_details.add(bd);

    }

}
