package com.example.beauty_salon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "role", schema = "salonbeauty", catalog = "")
@NoArgsConstructor

public class RoleEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_role", nullable = false)
    @Getter
    @Setter
    private int idRole;
    
    @Column(name = "role_name", nullable = true, length = 45)
    @Getter
    @Setter
    private String roleName;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "roleIdRole")
    @Getter
    @Setter
    private Collection<UsersHasRoleEntity> usersHasRolesByIdRole;

    public Collection<UsersHasRoleEntity> getUsersHasRolesByIdRole() {
        return usersHasRolesByIdRole;
    }

}
