package co.ratethem.controller;

import co.ratethem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserRepository userDao;

	
}
	

	
