package co.ratethem.payload;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewAddRequest {

    private String companyName;
    private String vacancyName;
    private String cityName;
    private Date startDate;
    private Date endDate;

    //HR Start
    private String hrName;
    private double hrIceBrake;
    private double hrAttitude;
    private double hrPunctuality;
    private double hrImpression;
    private String hrComment;
    //HR End

    //Tech Start
    private String techInterviewerName;
    private double techIceBrake;
    private double techAttitude;
    private double techQuestionsQuality;
    private double techImpression;
    private String techComment;
    //Tech End

    //Feedback Start
    private double feedbackOnTime;
    private double feedbackDetailization;
    private String feedbackComment;
    //Feedback End
}
