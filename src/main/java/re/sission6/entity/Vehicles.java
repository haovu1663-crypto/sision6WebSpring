package re.sission6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicles {
    //Trường: id, licensePlate (biển số xe), color (màu sắc), type (loại xe: CAR, BIKE - sử dụng Enum).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String licensePlate;
    @Column(length = 100, nullable = false)
    private String color;
    @Column(length = 100, nullable = false)
    private String type;
    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTickets> parkingTickets;
}
