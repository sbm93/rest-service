package com.example.login.rest_service;

import com.example.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDao;
import com.example.entity.User;

@RestController
//@RequestMapping(value="/api/")
public class LoginController {
	
	@Autowired
	private UserDao _userDao;
	  
	@RequestMapping("/home")
	public String home(){
		System.out.println("here in slash");
		return "Hello World!!";
	}
	
	@RequestMapping(value="/api/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@RequestMapping(value="/api/login", method = RequestMethod.GET)
	public ResponseEntity<Boolean> login() {
		/*System.out.println("user" + fields);
		User user = null;
		
		user = _userDao.getByEmail(fields.getEmail());

		 if(fields.getPassword().equals(user.getPassword())) {
*/
			 return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			 
		 /*}
		 
		 return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);*/
		 
	}
	
	@RequestMapping(value={ "/register/", "/list/" }, method=RequestMethod.POST )
	public String register(@RequestBody User user) {
		try {
			_userDao.save(user);	
	    }
	    catch(Exception ex) {
	      return ex.getMessage();
	    }
		return "User succesfully saved!";
	}
	
	@RequestMapping(value={ "/get/{id}"}, method=RequestMethod.GET )
	public User register(@PathVariable("id") long id) {
		System.out.println(id);
		User user = null;
		try {
		     user=_userDao.getById(id);
			System.out.println(user);
	    }
	    catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    }
		return  user;
	}
}
