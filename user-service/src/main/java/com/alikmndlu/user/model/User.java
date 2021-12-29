package com.alikmndlu.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = User.USER_TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static final String USER_TABLE_NAME = "users_table";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String emailAddress;
}
