package com.example.beauty_salon.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "users", schema = "salonbeauty", catalog = "")
@NoArgsConstructor

public class UsersEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_user", nullable = false)
    @Getter
    @Setter
    private int idUser;
    
    @Column(name = "user_name", nullable = true, length = 45)
    @Getter
    @Setter
    private String userName;
    
    @Column(name = "user_surname", nullable = true, length = 45)
    @Getter
    @Setter
    private String userSurname;
    
    @Column(name = "login", nullable = true, length = 45)
    @Getter
    @Setter
    private String login;
    
    @Column(name = "password", nullable = true, length = 45)
    @Getter
    @Setter
    private String password;
    
    @Column(name = "balance", nullable = true, length = 45)
    @Getter
    @Setter
    private Double balance;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usersIdUser", cascade = CascadeType.ALL)
    private Collection<UsersHasRoleEntity> usersHasRolesByIdUser;

    @Override
    public String toString() {
        return "UsersEntity{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance='" + balance +
                '}';
    }

}
