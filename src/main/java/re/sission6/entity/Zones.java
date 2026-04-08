package re.sission6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Zones {
    //Trường: id, name (tên khu vực), capacity (sức chứa tối đa), occupiedSpots (số chỗ đã dùng)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(length = 100,nullable = false)
    private String name;
    @Column(length = 100,nullable = false)
    private  int capacity;
    @Column(length = 100,nullable = false)
    private int occupiedSpots;

    @OneToMany(mappedBy = "zone")
    private List<ParkingTickets> parkingTickets;

}
