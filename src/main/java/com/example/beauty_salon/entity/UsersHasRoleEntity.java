package com.example.beauty_salon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_has_role", schema = "salonbeauty", catalog = "")
@IdClass(UsersHasRoleEntityPK.class)
@NoArgsConstructor
public class UsersHasRoleEntity {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "users_id_user", nullable = false)
    @Getter
    @Setter
    private int usersIdUser;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id_role", nullable = false)
    @Getter
    @Setter
    private int roleIdRole;

}
