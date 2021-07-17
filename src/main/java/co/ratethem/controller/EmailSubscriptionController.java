package co.ratethem.controller;

import co.ratethem.controller.rest_exception_handler.exception.EmptyValueException;
import co.ratethem.controller.rest_exception_handler.exception.InvalidValueException;
import co.ratethem.entity.EmailSubscription;
import co.ratethem.payload.CityResponse;
import co.ratethem.payload.EmailSubscriptionRequest;
import co.ratethem.service.CityService;
import co.ratethem.service.EmailSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/email/subscription")
public class EmailSubscriptionController {
	
	@Autowired
	private EmailSubscriptionService emailSubscriptionService;

	@PostMapping(path = "/add")
	public ResponseEntity<String> addEmailSubscription(@RequestBody EmailSubscriptionRequest emailSubscriptionRequest) throws InvalidValueException, EmptyValueException {

		emailSubscriptionService.addEmailSubscription(emailSubscriptionRequest);

		return new ResponseEntity<>("You have successfully subscribed for a newsletters", HttpStatus.OK);
	}

/*
	@GetMapping(path = "/all")
	public ResponseEntity<EmailSubscription> getAllEmails() throws InvalidValueException, EmptyValueException {

		List<EmailSubscription> emails = emailSubscriptionService.getAll();

		return new ResponseEntity(emails, HttpStatus.OK);
	}*/

}
	

	
