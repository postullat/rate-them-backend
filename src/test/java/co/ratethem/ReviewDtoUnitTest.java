package co.ratethem;

import co.ratethem.entity.Review;
import co.ratethem.payload.ReviewAddRequest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ReviewDtoUnitTest {

    private ModelMapper modelMapper = new ModelMapper();
    private static Review review;
    private static ReviewAddRequest reviewDto;

    @BeforeClass
    public static void initData() {

        //Dto Start
        reviewDto = new ReviewAddRequest();

        reviewDto.setCompanyName("NiX");
        reviewDto.setCityName("Львів");
        reviewDto.setVacancyName("Java Developer");

        Date now1Dto = new Date();
        Date now2Dto = new Date();
        reviewDto.setStartDate(now1Dto);
        reviewDto.setEndDate(now2Dto);

        reviewDto.setHrName("Ольга Данилишн");
        reviewDto.setHrIceBrake(4.5);
        reviewDto.setHrAttitude(3.5);
        reviewDto.setHrPunctuality(2.5);
        reviewDto.setHrImpression(1.5);
        reviewDto.setHrComment("Може бути");

        reviewDto.setTechInterviewerName("Oleg Davydiv");
        reviewDto.setTechIceBrake(4.5);
        reviewDto.setTechAttitude(3.5);
        reviewDto.setTechQuestionsQuality(2.5);
        reviewDto.setTechImpression(1.5);
        reviewDto.setTechComment("Дуже розчарований");

        reviewDto.setFeedbackOnTime(3.0);
        reviewDto.setFeedbackDetailization(2.0);
        reviewDto.setFeedbackComment("Жодного фідбеку, проста відписка");
        //Dto End


        //Entity Start
        review = new Review();

        review.setCompanyName("NiX");
        review.setCityName("Львів");
        review.setVacancyName("Java Developer");

        Date now1Entity = new Date();
        Date now2Entity = new Date();
        review.setStartDate(now1Entity);
        review.setEndDate(now2Entity);

        review.setHrName("Ольга Данилишн");
        review.setHrIceBrake(4.5);
        review.setHrAttitude(3.5);
        review.setHrPunctuality(2.5);
        review.setHrImpression(1.5);
        review.setHrComment("Може бути");

        review.setTechInterviewerName("Oleg Davydiv");
        review.setTechIceBrake(4.5);
        review.setTechAttitude(3.5);
        review.setTechQuestionsQuality(2.5);
        review.setTechImpression(1.5);
        review.setTechComment("Дуже розчарований");

        review.setFeedbackOnTime(3.0);
        review.setFeedbackDetailization(2.0);
        review.setFeedbackComment("Жодного фідбеку, проста відписка");
        //Entity End
    }

    @Test
    public void whenConvertReviewEntityToReviewDto_thenCorrect() {

        reviewDto = modelMapper.map(review, ReviewAddRequest.class);

        assertEquals(review.getCompanyName(), reviewDto.getCompanyName());
        assertEquals(review.getCityName(), reviewDto.getCityName());
        assertEquals(review.getVacancyName(), reviewDto.getVacancyName());

        assertEquals(review.getStartDate(), reviewDto.getStartDate());
        assertEquals(review.getEndDate(), reviewDto.getEndDate());

        assertEquals(review.getHrName(), reviewDto.getHrName());
        assertEquals(review.getHrIceBrake(), reviewDto.getHrIceBrake(), 0.2);
        assertEquals(review.getHrAttitude(), reviewDto.getHrAttitude(), 0.2);
        assertEquals(review.getHrPunctuality(), reviewDto.getHrPunctuality(), 0.2);
        assertEquals(review.getHrImpression(), reviewDto.getHrImpression(), 0.2);
        assertEquals(review.getHrComment(), reviewDto.getHrComment());

        assertEquals(review.getTechInterviewerName(), reviewDto.getTechInterviewerName());
        assertEquals(review.getTechIceBrake(), reviewDto.getTechIceBrake(), 0.2);
        assertEquals(review.getTechAttitude(), reviewDto.getTechAttitude(), 0.2);
        assertEquals(review.getTechQuestionsQuality(), reviewDto.getTechQuestionsQuality(), 0.2);
        assertEquals(review.getTechImpression(), reviewDto.getTechImpression(), 0.2);
        assertEquals(review.getTechComment(), reviewDto.getTechComment());

        assertEquals(review.getFeedbackOnTime(), reviewDto.getFeedbackOnTime(), 0.2);
        assertEquals(review.getFeedbackDetailization(), reviewDto.getFeedbackDetailization(), 0.2);
        assertEquals(review.getFeedbackComment(), reviewDto.getFeedbackComment());

    }

    @Test
    public void whenConvertReviewDtoToReviewEntity_thenCorrect() {

        review = modelMapper.map(reviewDto, Review.class);

        assertEquals(reviewDto.getCompanyName(), review.getCompanyName());
        assertEquals(reviewDto.getCityName(), review.getCityName());
        assertEquals(reviewDto.getVacancyName(), review.getVacancyName());

        assertEquals(reviewDto.getStartDate(), review.getStartDate());
        assertEquals(reviewDto.getEndDate(), review.getEndDate());

        assertEquals(reviewDto.getHrName(), review.getHrName());
        assertEquals(reviewDto.getHrIceBrake(), review.getHrIceBrake(), 0.2);
        assertEquals(reviewDto.getHrAttitude(), review.getHrAttitude(), 0.2);
        assertEquals(reviewDto.getHrPunctuality(), review.getHrPunctuality(), 0.2);
        assertEquals(reviewDto.getHrImpression(), review.getHrImpression(), 0.2);
        assertEquals(reviewDto.getHrComment(), review.getHrComment());

        assertEquals(reviewDto.getTechInterviewerName(), review.getTechInterviewerName());
        assertEquals(reviewDto.getTechIceBrake(), review.getTechIceBrake(), 0.2);
        assertEquals(reviewDto.getTechAttitude(), review.getTechAttitude(), 0.2);
        assertEquals(reviewDto.getTechQuestionsQuality(), review.getTechQuestionsQuality(), 0.2);
        assertEquals(reviewDto.getTechImpression(), review.getTechImpression(), 0.2);
        assertEquals(reviewDto.getTechComment(), review.getTechComment());

        assertEquals(reviewDto.getFeedbackOnTime(), review.getFeedbackOnTime(), 0.2);
        assertEquals(reviewDto.getFeedbackDetailization(), review.getFeedbackDetailization(), 0.2);
        assertEquals(reviewDto.getFeedbackComment(), review.getFeedbackComment());

    }
}