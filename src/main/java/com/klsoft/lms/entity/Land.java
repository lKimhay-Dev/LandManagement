package com.klsoft.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity(name = "lands")
public class Land implements Serializable {

    @Id
    @Column(name = "id")
    private String plotNo;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private Float size;

    @Column(name = "width")
    private Float width;

    @Column(name = "length")
    private Float length;

    @Column(name = "location")
    private String location;

    @Column(name = "title_type")
    private String titleType;

    private Double price;
    private Boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @JsonIgnore
    @OneToOne(mappedBy = "land")
    private SellLandDetail sellLandDetail;

    @JsonIgnore
    @OneToOne(mappedBy = "land")
    private Deposit deposit;

    @JsonIgnore
    @OneToOne(mappedBy = "land")
    private Installment installment;

    @JsonIgnore
    @OneToOne(mappedBy = "land")
    private CustomerLandTitle customerLandTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "land")
    private Set<Penalty> penalties;

}
