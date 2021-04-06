package com.klsoft.lms.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity(name = "projects")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String location;
    private Float size;
    private String address;
    private Double price;
    private Boolean status;

    @OneToMany(mappedBy = "project")
    private Set<Land> lands;
}
