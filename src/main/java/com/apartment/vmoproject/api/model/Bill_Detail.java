package com.apartment.vmoproject.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "bill_detail")
public class Bill_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id", nullable = false)
    @JsonIgnoreProperties("bill_details")
    private Bill bill;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "service_fee_id",nullable = false)
    @JsonIgnoreProperties("bill_details")
    private Service_Fee service_fee;


}
