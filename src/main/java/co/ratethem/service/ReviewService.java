package co.ratethem.service;

import co.ratethem.entity.Review;
import co.ratethem.payload.ReviewAddRequest;
import co.ratethem.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void add(ReviewAddRequest reviewAddRequest) {

        //TODO: validate dto

        //convert dto to entity
        Review review = modelMapper.map(reviewAddRequest, Review.class);
        review.setCreated(new Date());

        //save entity
        reviewRepository.save(review);

    }
}
