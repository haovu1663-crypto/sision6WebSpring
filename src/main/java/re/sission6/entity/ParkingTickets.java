package re.sission6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ParkingTickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private LocalDateTime checkIn;
    @Column(length = 100, nullable = true)
    private LocalDateTime checkOut;
    @JoinColumn(name ="vehicle_id")
    @ManyToOne()
    private Vehicles vehicle;
    @JoinColumn(name ="zone_id")
    @ManyToOne()
    private Zones zone;
}
