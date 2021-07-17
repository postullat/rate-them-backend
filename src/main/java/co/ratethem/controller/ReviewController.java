package co.ratethem.controller;

import co.ratethem.controller.rest_exception_handler.exception.EmptyValueException;
import co.ratethem.controller.rest_exception_handler.exception.InvalidValueException;
import co.ratethem.payload.ReviewRequest;
import co.ratethem.repository.ReviewRepository;
import co.ratethem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "api/review")
//@CrossOrigin(origins = {"https://rate-them.in.ua", "http://technicalinterviewsinukrainemustbebetterpreparedandmorepleasant.com"})
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addReview(@RequestBody ReviewRequest reviewAddRequest) throws EmptyValueException, InvalidValueException {
		reviewService.add(reviewAddRequest);
	}

		
	@RequestMapping(value = "/getLastAdded/{page}/{amount}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getLastAddedReviews(
			@PathVariable Integer page,
			@PathVariable Integer amount) {

		Map<String, Object> lastAdded = reviewService.getLastCreated(page, amount);
		return new ResponseEntity<>(lastAdded, HttpStatus.OK);
	}

	@RequestMapping(value = "/find/by/{page}/{amount}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findReviewsBy(
			List<Long> companyIds,
			List<Long> cityIds,
			String hrName,
			String techInterviewerName,

			@PathVariable Integer page,
			@PathVariable Integer amount) {

		Map<String, Object> lastAdded = reviewService.getLastCreated(page, amount);
		return new ResponseEntity<>(lastAdded, HttpStatus.OK);
	}

}
	

	
