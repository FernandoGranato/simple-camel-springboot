package com.camel.example.simplecamelspringboot.beans;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "NAME_ADDRESS")
@NamedQuery(name = "fetchAllRows", query = "select x from NameAddress x")
public class NameAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;

    @Column(name = "CITY")
    private String city;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "POSTAL_CODE")
    private String postalCode;
}
