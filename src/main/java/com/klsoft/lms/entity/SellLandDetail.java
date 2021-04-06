package com.klsoft.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity()
@Table(name = "sell_land_details")
public class SellLandDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_price")
    private double totalPrice;

    private double discount;

    @Column(name = "final_price")
    private double finalPrice;

    @Column(name = "buying_contract")
    private String buyingContract;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sell_id", nullable = false)
    private Sell sell;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "land_id", unique = true, nullable = false)
    private Land land;
}
