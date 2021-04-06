package com.klsoft.lms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity()
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dob")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dob;

    @Column(name = "address")
    private String address;

    @Column(name = "id_card", unique = true)
    private String idCard;

    @Column(name = "id_card_no", unique = true)
    private String idCardNo;

    @Column(name = "id_card_issue_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date idCardIssueDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "phone_1")
    private String phone1;

    @Column(name = "phone_2")
    private String phone2;

    private boolean status;

    @JsonIgnore
    @ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    private Set<Sell> sells;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Deposit> deposits;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Penalty> penalties;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Installment> installments;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<CustomerLandTitle> customerLandTitles;

}
