package com.example.beauty_salon.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bonus", schema = "salonbeauty", catalog = "")
@NoArgsConstructor
public class BonusEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bonus_id", nullable = false)
    @Getter
    @Setter
    private int bonusId;
    
    @Column(name = "discount", nullable = true)
    @Getter
    @Setter
    private int discount;
    
    @Column(name = "certificate", nullable = true)
    @Getter
    @Setter
    private int certificate;
    
    @Column(name = "id_user", insertable = false, updatable = false, nullable = false)
    @Getter
    @Setter
    private int idUser;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @Getter
    @Setter
    private UsersEntity usersByIdUser;

}