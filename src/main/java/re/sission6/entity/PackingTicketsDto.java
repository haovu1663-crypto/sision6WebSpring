package re.sission6.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PackingTicketsDto {
    private int id ;
    private LocalDate checkin;
    private LocalDate checkout;
    private int vehicle;
    private int zone;
}
