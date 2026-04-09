package re.sission6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer id;
    @Column(length = 100,nullable = false)
    private String name;
    @Column(length = 100,nullable = false)
    private Integer capacity;      // ✅ Integer thay vì int

    @Column(length = 100,nullable = false)

    private Integer occupiedSpots;
    @JsonIgnore
    @OneToMany(mappedBy = "zone")
    private List<ParkingTickets> parkingTickets;

}
