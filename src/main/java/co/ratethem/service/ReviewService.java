package co.ratethem.service;

import co.ratethem.controller.rest_exception_handler.exception.EmptyValueException;
import co.ratethem.controller.rest_exception_handler.exception.InvalidValueException;
import co.ratethem.entity.Review;
import co.ratethem.payload.ReviewRequest;
import co.ratethem.payload.ReviewResponse;
import co.ratethem.repository.ReviewRepository;
import io.swagger.models.auth.In;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
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

    private ReviewValidator validator;

    public void add(ReviewRequest reviewJson) throws EmptyValueException, InvalidValueException {

        //check if null
        validator.validateRequest(reviewJson);

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
        if (amount > 10) {
            amount = 10;
        }

        Page<Review> lastAddedReview = reviewRepository.findLastAdded(PageRequest.of(page, amount));


        //convert repository model into dto
        List<Review> reviews = lastAddedReview.getContent();
        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<ReviewResponse>>() {
        }.getType();
        List<ReviewResponse> reviewDto = modelMapper.map(reviews, targetListType);

        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewDto);
        response.put("currentPage", lastAddedReview.getNumber());
        response.put("totalItems", lastAddedReview.getTotalElements());
        response.put("totalPages", lastAddedReview.getTotalPages());

        return response;
    }
}
