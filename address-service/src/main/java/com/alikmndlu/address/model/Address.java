package com.alikmndlu.address.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = Address.ADDRESS_TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    public static final String ADDRESS_TABLE_NAME = "addresses_table";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String address;
}
