package co.ratethem.controller;

import co.ratethem.payload.CityResponse;
import co.ratethem.payload.CompanyResponse;
import co.ratethem.service.CityService;
import co.ratethem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/city")
public class CityController {
	
	@Autowired
	private CityService cityService;


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<CityResponse>> findAllCities() {

		List<CityResponse> cities = cityService.findAll();

		if (cities == null || cities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(cities, HttpStatus.OK);
	}

}
	

	
