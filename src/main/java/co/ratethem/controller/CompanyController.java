package co.ratethem.controller;

import co.ratethem.payload.CompanyResponse;
import co.ratethem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;


		
	@RequestMapping(value = "/name/contains", method = RequestMethod.POST)
	public ResponseEntity<List<CompanyResponse>> findCompanyByNameLike(@RequestParam(required = false) String chars) {

		List<CompanyResponse> companies = null;

		//if no params then fetch top 10
		if(StringUtils.isEmpty(chars)) {
			companies = companyService.findFirst10();
		} else {
			companies = companyService.findByChars(chars);

		}

		if (companies == null || companies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(companies, HttpStatus.OK);
	}

}
	

	
