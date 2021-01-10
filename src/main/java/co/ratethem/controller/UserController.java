package co.ratethem.controller;

import co.ratethem.entity.User;
import co.ratethem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserRepository userDao;


	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public User addUser(@RequestBody User user) {
		return userDao.save(user);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<User> viewAll() {
	
		return userDao.findAll();
	}
		
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<User> getUserById(@PathVariable Long id) {
	
		
		return userDao.findById(id);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteUserById(@PathVariable Long id) {
		
		userDao.deleteById(id);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void editUser(@RequestBody User user) {
		
		userDao.save(user);
	}
	
}
	

	
