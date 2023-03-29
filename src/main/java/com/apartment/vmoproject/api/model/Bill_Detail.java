package com.apartment.vmoproject.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "bill_detail")
public class Bill_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    @JsonBackReference
    private Bill bill;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "service_fee_id")
    @JsonBackReference
    private Service_Fee service_fee;


}
