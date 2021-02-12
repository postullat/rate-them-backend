package co.ratethem.payload;

import co.ratethem.entity.enums.EntityStatus;
import lombok.Data;

@Data
public class CityResponse {

    private Long id;

    private String name;

    private EntityStatus status;
}
