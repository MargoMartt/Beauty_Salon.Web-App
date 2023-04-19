package com.example.beauty_salon.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "beauty_masters", schema = "salonbeauty", catalog = "")
@NoArgsConstructor
public class BeautyMastersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "master_id", nullable = false)
    @Getter
    @Setter
    private int masterId;
    
    @Column(name = "master_surname", nullable = true, length = 60)
    @Getter
    @Setter
    private String masterSurname;
    
    @Column(name = "master_name", nullable = true, length = 60)
    @Getter
    @Setter
    private String masterName;
    
    @Column(name = "activity", nullable = true, length = 100)
    @Getter
    @Setter
    private String activity;
    
    @Column(name = "work_experience", nullable = true)
    @Getter
    @Setter
    private Integer workExperience;
    @OneToMany(mappedBy = "serviceId")
    @Getter
    @Setter
    private Collection<ServiceEntity> servicesByMasterId;
    
    @Override
    public String toString() {
        return "BeautyMastersEntity{" +
                "masterId=" + masterId +
                ", masterSurname='" + masterSurname + '\'' +
                ", masterName='" + masterName + '\'' +
                ", activity='" + activity + '\'' +
                ", workExperience=" + workExperience +
                '}';
    }
}
