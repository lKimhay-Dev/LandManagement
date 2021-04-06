package com.klsoft.lms.dto;

import lombok.Data;

@Data
public class ProjectDto {
    private Integer id;
    private String name;
    private String location;
    private float size;
    private String address;
    private double price;
    private boolean status;
}

