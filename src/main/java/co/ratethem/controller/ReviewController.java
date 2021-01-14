package co.ratethem.controller;

import co.ratethem.entity.Review;
import co.ratethem.payload.ReviewAddRequest;
import co.ratethem.payload.ReviewJson;
import co.ratethem.repository.ReviewRepository;
import co.ratethem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "api/review")
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ReviewService reviewService;


/*	@CrossOrigin
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addReview(@RequestBody ReviewAddRequest reviewAddRequest) {
		reviewService.add(reviewAddRequest);
	}*/

	@CrossOrigin
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addReview(@RequestBody ReviewJson reviewAddRequest) {
		reviewService.add(reviewAddRequest);
	}
	
	@CrossOrigin
	@GetMapping(value = "/all")
	public ResponseEntity<List<Review>> viewAll() {
	
		return new ResponseEntity<>(reviewRepository.findAll(), HttpStatus.OK);
	}
	

		
	@CrossOrigin
	@RequestMapping(value = "/getLastAdded/{page}/{amount}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getLastAddedReviews(
			@PathVariable Integer page,
			@PathVariable Integer amount) {

		Map<String, Object> lastAdded = reviewService.getLastCreated(page, amount);
		return new ResponseEntity<>(lastAdded, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteReviewById(@PathVariable Long id) {
		
		reviewRepository.deleteById(id);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void editReview(@RequestBody Review featureFilm) {

		reviewRepository.save(featureFilm);
	}
	
}
	

	
