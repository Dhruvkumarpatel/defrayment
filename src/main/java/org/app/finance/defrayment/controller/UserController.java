package org.app.finance.defrayment.controller;

import org.app.finance.defrayment.dto.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.USER_BASE_URI)
public class UserController {
	
	public static final String USER_BASE_URI = "api/v1/users";
	
	@RequestMapping(value = "{userId}")
	public User getUser(@PathVariable final int userId){
		User user = new User();
		
		user.setUserId(userId);
		user.setName("Snehit");
		user.setState("KS");
		
		return user;
	}
}
