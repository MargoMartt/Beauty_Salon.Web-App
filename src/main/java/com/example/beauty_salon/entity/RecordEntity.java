package com.example.beauty_salon.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "record", schema = "salonbeauty", catalog = "")
public class RecordEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "record_id", nullable = false)
    @Getter
    @Setter
    private int recordId;
    
    @Column(name = "time", nullable = true)
    @Getter
    @Setter
    private String time;
    
    @Column(name = "date", nullable = true)
    @Getter
    @Setter
    private Date date;
    
    @Column(name = "total_cost", nullable = true, precision = 0)
    @Getter
    @Setter
    private Double totalCost;
    
    @Column(name = "id_user", insertable = false, updatable = false, nullable = false)
    @Getter
    @Setter
    private int idUser;
    
    @Column(name = "service_id", insertable = false, updatable = false, nullable = false)
    @Getter
    @Setter
    private int serviceId;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @Getter
    @Setter
    private UsersEntity usersByIdUser;
    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", nullable = false)
    @Getter
    @Setter
    private ServiceEntity serviceByserviceId;
    
    @Override
    public String toString() {
        return "RecordEntity{" +
                "recordId=" + recordId +
                ", time='" + time + '\'' +
                ", date=" + date +
                ", totalCost=" + totalCost +
                ", idUser=" + idUser +
                ", serviceId=" + serviceId +
                ", usersByIdUser=" + usersByIdUser +
                ", serviceByserviceId=" + serviceByserviceId +
                '}';
    }
}
