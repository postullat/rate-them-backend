package co.ratethem.payload;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewRequest {
    private String vacancyName;
    private long companyId;
    private long cityId;
    private Date startDate;
    private Date endDate;
    private Hr hr;
    private Tech tech;
    private Feedback feedback;
}
