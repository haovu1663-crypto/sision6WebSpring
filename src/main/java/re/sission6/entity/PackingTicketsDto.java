package re.sission6.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PackingTicketsDto {
    private int id ;
    private LocalDateTime checkin;  // ✅ Sửa
    private LocalDateTime checkout; // ✅ Sửa
    private int vehicle;
    private int zone;
}
