package com.klsoft.lms.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity()
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;

    @OneToOne()
    @JoinColumn(name = "seller_id", referencedColumnName = "id", unique = true)
    private Seller seller;
}
