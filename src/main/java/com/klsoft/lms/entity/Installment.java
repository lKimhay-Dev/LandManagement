package com.klsoft.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity()
@Table(name = "installments")
public class Installment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payment_date")
    private Date paymentDate;

    private int duration;

    @Column(name = "annual_interest")
    private double annualInterest;

    private double amount;

    @Column(name = "penalty_rate")
    private double penaltyRate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne()
    @JoinColumn(name = "land_id", referencedColumnName = "id", unique = true)
    private Land land;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

}
