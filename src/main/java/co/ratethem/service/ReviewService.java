package co.ratethem.service;

import co.ratethem.entity.Review;
import co.ratethem.payload.ReviewAddRequest;
import co.ratethem.payload.ReviewJson;
import co.ratethem.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void add(ReviewJson reviewAddRequest) {

        //TODO: validate dto

        //convert dto to entity
        Review review = modelMapper.map(reviewAddRequest, Review.class);
        review.setCreated(new Date());

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
        java.lang.reflect.Type targetListType = new TypeToken<List<ReviewJson>>() {}.getType();
        List<ReviewJson> reviewDto = modelMapper.map(reviews, targetListType);

        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewDto);
        response.put("currentPage", lastAddedReview.getNumber());
        response.put("totalItems", lastAddedReview.getTotalElements());
        response.put("totalPages", lastAddedReview.getTotalPages());

        return response;
    }
}
