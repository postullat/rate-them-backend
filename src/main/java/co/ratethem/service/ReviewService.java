package co.ratethem.service;

import co.ratethem.controller.rest_exception_handler.exception.EmptyValueException;
import co.ratethem.controller.rest_exception_handler.exception.InvalidValueException;
import co.ratethem.entity.Review;
import co.ratethem.payload.ReviewRequest;
import co.ratethem.payload.ReviewResponse;
import co.ratethem.repository.ReviewRepository;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void add(ReviewRequest reviewJson) throws EmptyValueException, InvalidValueException {

        //TODO: validate dto
        if(StringUtils.isEmpty(reviewJson.getVacancyName())) {
            throw new EmptyValueException("Vacancy name can't be null or empty");
        }

        if(StringUtils.isEmpty(reviewJson.getCityName())) {
            throw new EmptyValueException("City name can't be null or empty");
        }

        if(StringUtils.isEmpty(reviewJson.getCompanyName())) {
            throw new EmptyValueException("Company name can't be null or empty");
        }


        if(reviewJson.getStartDate() == null) {
            throw new EmptyValueException("Start date can't be null or empty");
        }

        DateTime now = DateTime.now();
        DateTime startDate = new DateTime(reviewJson.getStartDate());
        DateTime endDate = new DateTime(reviewJson.getEndDate());

        if(startDate.isAfter(now)) {
            throw new InvalidValueException("Interview Start Date can't be after Now");
        }

        if(endDate.isAfter(now)) {
            throw new InvalidValueException("Interview End Date can't be after Now");
        }

        if(endDate.isAfter(startDate)) {
            throw new InvalidValueException("Interview Start Date can't be after End Date");
        }

        //HR validation Start
        if(StringUtils.isEmpty(reviewJson.getHr().getName())) {
            throw new EmptyValueException("HR Name can't be null or empty");
        }

        if(reviewJson.getHr().getIceBrake() <= 0 || reviewJson.getHr().getIceBrake() > 5) {
            throw new InvalidValueException("HR Ice Brake value should be an integer and value should be between 1 and 5");
        }

        if(reviewJson.getHr().getAttitude() <= 0 || reviewJson.getHr().getAttitude() > 5) {
            throw new InvalidValueException("HR Attitude value should be an integer and value should be between 1 and 5");
        }

        if(reviewJson.getHr().getPunctuality() <= 0 || reviewJson.getHr().getPunctuality() > 5) {
            throw new InvalidValueException("HR Punctuality value should be an integer and value should be between 1 and 5");
        }

        if(reviewJson.getHr().getImpression() <= 0 || reviewJson.getHr().getImpression() > 5) {
            throw new InvalidValueException("HR Impression value should be an integer and value should be between 1 and 5");
        }

        if(!StringUtils.isEmpty(reviewJson.getHr().getComment())) {
            if(reviewJson.getHr().getComment().length() > 500)
            throw new EmptyValueException("Comment about HR can't be more than 500 symbols");
        }
        //HR validation End

        //Tech validation Start
        if(StringUtils.isEmpty(reviewJson.getTech().getInterviewerName())) {
            throw new EmptyValueException("Tech Interviewer Name can't be null or empty");
        }

        if(reviewJson.getTech().getIceBrake() <= 0 || reviewJson.getTech().getIceBrake() > 5) {
            throw new InvalidValueException("Tech Interviewer Ice Brake value should be an integer and value should be between 1 and 5");
        }

        if(reviewJson.getTech().getAttitude() <= 0 || reviewJson.getTech().getAttitude() > 5) {
            throw new InvalidValueException("Tech Interviewer Attitude value should be an integer and value should be between 1 and 5");
        }

        if(reviewJson.getTech().getQuestionsQuality() <= 0 || reviewJson.getTech().getQuestionsQuality() > 5) {
            throw new InvalidValueException("Tech Interviewer Questions Quality value should be an integer and value should be between 1 and 5");
        }

        if(reviewJson.getTech().getImpression() <= 0 || reviewJson.getTech().getImpression() > 5) {
            throw new InvalidValueException("Tech Interviewer Impression value should be an integer and value should be between 1 and 5");
        }

        if(!StringUtils.isEmpty(reviewJson.getTech().getComment())) {
            if(reviewJson.getTech().getComment().length() > 500)
                throw new EmptyValueException("Comment about Tech Interviewer can't be more than 500 symbols");
        }
        //Tech validation End

        //Feedback validation Start
        if(reviewJson.getFeedback().getOnTime() <= 0 || reviewJson.getFeedback().getOnTime() > 5) {
            throw new InvalidValueException("Feedback On Time value should be an integer and value should be between 1 and 5");
        }

        if(reviewJson.getFeedback().getDetailization() <= 0 || reviewJson.getFeedback().getDetailization() > 5) {
            throw new InvalidValueException("Feedback Detailization value should be an integer and value should be between 1 and 5");
        }

        if(!StringUtils.isEmpty(reviewJson.getFeedback().getComment())) {
            if(reviewJson.getFeedback().getComment().length() > 500)
                throw new EmptyValueException("Comment about Feedback can't be more than 500 symbols");
        }
        //Feedback validation End


        //convert dto to entity
        Review review = modelMapper.map(reviewJson, Review.class);
        review.setCreated(new Date());

        double hrRatingTotal =
                (review.getHrIceBrake()
                + review.getHrAttitude()
                + review.getHrPunctuality()
                + review.getHrImpression()) / 4.0;

        double techRatingTotal =
                (review.getTechIceBrake()
                + review.getTechAttitude()
                + review.getTechQuestionsQuality()
                + review.getTechImpression()) / 4.0;

        double feedbackRatingTotal =
                (review.getFeedbackOnTime()
                + review.getFeedbackDetailization()) / 2.0;

        double companyRatingTotal = (hrRatingTotal + techRatingTotal + feedbackRatingTotal) / 3.0;

        review.setHrRatingTotal(hrRatingTotal);
        review.setTechRatingTotal(techRatingTotal);
        review.setFeedbackRatingTotal(feedbackRatingTotal);

        review.setCompanyRatingTotal(companyRatingTotal);

        //save entity
        reviewRepository.save(review);

    }


    //TODO: write unit test for this method
    public Map<String, Object> getLastCreated(int page, int amount) {

        //allow max amount be 10
        if(amount > 10) {
            amount = 10;
        }

        Page<Review> lastAddedReview = reviewRepository.findLastAdded(PageRequest.of(page, amount));

       
        //convert repository model into dto
        List<Review> reviews = lastAddedReview.getContent();
        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<ReviewResponse>>() {}.getType();
        List<ReviewResponse> reviewDto = modelMapper.map(reviews, targetListType);

        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewDto);
        response.put("currentPage", lastAddedReview.getNumber());
        response.put("totalItems", lastAddedReview.getTotalElements());
        response.put("totalPages", lastAddedReview.getTotalPages());

        return response;
    }
}
