package com.eximius.eximius.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String userName;
    private String password;

    //We use fetchType in EAGER so that every time a user is accessed or extracted from the DB, it extracts all its roles.
    /* With JoinTable we will be creating a table that will join the user and role table, so we will have a total of 3 related tables in the “users_roles” table, through its user_id columns, which will point to the ID of the user table and role_id which will point to the ID of the role table.
    which will point to the ID of the user table and role_id which will point to the ID of the role table */

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user")
            , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"))

    private List<Role> roles = new ArrayList<>();

}