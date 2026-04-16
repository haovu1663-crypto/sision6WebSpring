package re.sission6.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.domain.Page;
import re.sission6.entity.Vehicles;

import java.util.List;

@Getter
@Setter


public class ApiResponse {
    private Boolean Success;
    private String Message;
    private Page<Vehicles> data;

    public ApiResponse(Page<Vehicles> vehicles) {
           Success = true;
           Message = "Success";
           data = vehicles ;
    }
}
