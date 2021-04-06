package com.klsoft.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity()
@Table(name = "sells")
public class Sell implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_installment")
    private Boolean isInstallment = true;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sells_customers",
            joinColumns = {@JoinColumn(name = "sell_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private Set<Customer> customers;

    @OneToMany(mappedBy = "sell", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = false)
    private Set<SellLandDetail> sellLandDetails;

}
