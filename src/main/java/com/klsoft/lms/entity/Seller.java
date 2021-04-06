package com.klsoft.lms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity()
@Table(name = "sellers")
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_1", nullable = false)
    private String phone1;

    @Column(name = "phone_2")
    private String phone2;

    @Column(name = "created_date",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = false
    )

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date createdDate = new Date();

    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;

    @JsonIgnore
    @OneToOne(mappedBy = "seller")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private Set<Sell> sells;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private Set<Installment> installments;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private Set<Deposit> deposits;
}
