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
    private int hrIceBrake;
    private int hrAttitude;
    private int hrPunctuality;
    private int hrImpression;
    private String hrComment;
    //HR End

    //Tech Start
    private String techInterviewerName;
    private int techIceBrake;
    private int techAttitude;
    private int techQuestionsQuality;
    private int techImpression;
    private String techComment;
    //Tech End

    //Feedback Start
    private int feedbackOnTime;
    private int feedbackDetailization;
    private String feedbackComment;
    //Feedback End
}
