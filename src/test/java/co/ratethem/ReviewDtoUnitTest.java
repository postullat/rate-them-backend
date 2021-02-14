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

        //reviewDto.setCompanyName("NiX");
        //reviewDto.setCityName("Львів");
        reviewDto.setVacancyName("Java Developer");

        Date now1Dto = new Date();
        Date now2Dto = new Date();
        reviewDto.setStartDate(now1Dto);
        reviewDto.setEndDate(now2Dto);

        reviewDto.setHrName("Ольга Данилишн");
        reviewDto.setHrIceBrake(4);
        reviewDto.setHrAttitude(3);
        reviewDto.setHrPunctuality(2);
        reviewDto.setHrImpression(1);
        reviewDto.setHrComment("Може бути");

        reviewDto.setTechInterviewerName("Oleg Davydiv");
        reviewDto.setTechIceBrake(4);
        reviewDto.setTechAttitude(3);
        reviewDto.setTechQuestionsQuality(2);
        reviewDto.setTechImpression(1);
        reviewDto.setTechComment("Дуже розчарований");

        reviewDto.setFeedbackOnTime(3);
        reviewDto.setFeedbackDetailization(2);
        reviewDto.setFeedbackComment("Жодного фідбеку, проста відписка");
        //Dto End


        //Entity Start
        review = new Review();

        //review.setCompanyName("NiX");
        //review.setCityName("Львів");
        review.setVacancyName("Java Developer");

        Date now1Entity = new Date();
        Date now2Entity = new Date();
        review.setStartDate(now1Entity);
        review.setEndDate(now2Entity);

        review.setHrName("Ольга Данилишн");
        review.setHrIceBrake(4);
        review.setHrAttitude(3);
        review.setHrPunctuality(2);
        review.setHrImpression(1);
        review.setHrComment("Може бути");

        review.setTechInterviewerName("Oleg Davydiv");
        review.setTechIceBrake(4);
        review.setTechAttitude(3);
        review.setTechQuestionsQuality(2);
        review.setTechImpression(1);
        review.setTechComment("Дуже розчарований");

        review.setFeedbackOnTime(3);
        review.setFeedbackDetailization(2);
        review.setFeedbackComment("Жодного фідбеку, проста відписка");
        //Entity End
    }

    @Test
    public void whenConvertReviewEntityToReviewDto_thenCorrect() {

        reviewDto = modelMapper.map(review, ReviewAddRequest.class);

        //assertEquals(review.getCompanyName(), reviewDto.getCompanyName());
        //assertEquals(review.getCityName(), reviewDto.getCityName());
        assertEquals(review.getVacancyName(), reviewDto.getVacancyName());

        assertEquals(review.getStartDate(), reviewDto.getStartDate());
        assertEquals(review.getEndDate(), reviewDto.getEndDate());

        assertEquals(review.getHrName(), reviewDto.getHrName());
        assertEquals(review.getHrIceBrake(), reviewDto.getHrIceBrake());
        assertEquals(review.getHrAttitude(), reviewDto.getHrAttitude());
        assertEquals(review.getHrPunctuality(), reviewDto.getHrPunctuality());
        assertEquals(review.getHrImpression(), reviewDto.getHrImpression());
        assertEquals(review.getHrComment(), reviewDto.getHrComment());

        assertEquals(review.getTechInterviewerName(), reviewDto.getTechInterviewerName());
        assertEquals(review.getTechIceBrake(), reviewDto.getTechIceBrake());
        assertEquals(review.getTechAttitude(), reviewDto.getTechAttitude());
        assertEquals(review.getTechQuestionsQuality(), reviewDto.getTechQuestionsQuality());
        assertEquals(review.getTechImpression(), reviewDto.getTechImpression());
        assertEquals(review.getTechComment(), reviewDto.getTechComment());

        assertEquals(review.getFeedbackOnTime(), reviewDto.getFeedbackOnTime());
        assertEquals(review.getFeedbackDetailization(), reviewDto.getFeedbackDetailization());
        assertEquals(review.getFeedbackComment(), reviewDto.getFeedbackComment());

    }

    @Test
    public void whenConvertReviewDtoToReviewEntity_thenCorrect() {

        review = modelMapper.map(reviewDto, Review.class);

        //assertEquals(reviewDto.getCompanyName(), review.getCompanyName());
        //assertEquals(reviewDto.getCityName(), review.getCityName());
        assertEquals(reviewDto.getVacancyName(), review.getVacancyName());

        assertEquals(reviewDto.getStartDate(), review.getStartDate());
        assertEquals(reviewDto.getEndDate(), review.getEndDate());

        assertEquals(reviewDto.getHrName(), review.getHrName());
        assertEquals(reviewDto.getHrIceBrake(), review.getHrIceBrake());
        assertEquals(reviewDto.getHrAttitude(), review.getHrAttitude());
        assertEquals(reviewDto.getHrPunctuality(), review.getHrPunctuality());
        assertEquals(reviewDto.getHrImpression(), review.getHrImpression());
        assertEquals(reviewDto.getHrComment(), review.getHrComment());

        assertEquals(reviewDto.getTechInterviewerName(), review.getTechInterviewerName());
        assertEquals(reviewDto.getTechIceBrake(), review.getTechIceBrake());
        assertEquals(reviewDto.getTechAttitude(), review.getTechAttitude());
        assertEquals(reviewDto.getTechQuestionsQuality(), review.getTechQuestionsQuality());
        assertEquals(reviewDto.getTechImpression(), review.getTechImpression());
        assertEquals(reviewDto.getTechComment(), review.getTechComment());

        assertEquals(reviewDto.getFeedbackOnTime(), review.getFeedbackOnTime());
        assertEquals(reviewDto.getFeedbackDetailization(), review.getFeedbackDetailization());
        assertEquals(reviewDto.getFeedbackComment(), review.getFeedbackComment());

    }
}