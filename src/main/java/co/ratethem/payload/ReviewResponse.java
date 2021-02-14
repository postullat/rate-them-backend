package co.ratethem.payload;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewResponse {
    private String vacancyName;
    private String companyId;
    private String companyName;
    private String cityId;
    private String cityName;
    private Date startDate;
    private Date endDate;
    private Hr hr;
    private Tech tech;
    private Feedback feedback;

    private double hrRatingTotal;
    private double techRatingTotal;
    private double feedbackRatingTotal;
    private double companyRatingTotal;
}
