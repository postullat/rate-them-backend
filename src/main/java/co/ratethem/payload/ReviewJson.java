package co.ratethem.payload;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewJson {
    private String vacancyName;
    private String companyName;
    private String cityName;
    private Date startDate;
    private Date endDate;
    private Hr hr;
    private Tech tech;
    private Feedback feedback;
}
