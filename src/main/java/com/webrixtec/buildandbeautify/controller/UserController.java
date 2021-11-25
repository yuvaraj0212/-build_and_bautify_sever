package com.webrixtec.buildandbeautify.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webrixtec.buildandbeautify.exception.ExceptionController;
import com.webrixtec.buildandbeautify.model.UserModel;
import com.webrixtec.buildandbeautify.pojo.LoginRequest;
import com.webrixtec.buildandbeautify.repo.UserRepo;
import com.webrixtec.buildandbeautify.service.UserService;


@RestController
@CrossOrigin
public class UserController extends ExceptionController {
	
	
	@Autowired
	UserService userService;
	
	@PostMapping(value="/signin")
	public Object authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return userService.signin(loginRequest);
	}
	
	@PostMapping(value = "/signup")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserModel userModel){
		return userService.signUp(userModel);
	}	
	
	@GetMapping(value="/user-list")
	public ResponseEntity<Object> getUserList(){
		return userService.getUserList();
	}

	
//	@GetMapping(value="/forget-password")
//	public ResponseEntity<Object> forgotPassword(@RequestParam(name="emailId")String emailId){
//		return userService.forgotPassword(emailId);
//	}
//	
//	@PostMapping(value="/reset-password/{emailId}")
//	public ResponseEntity<Object> resetPassword(@PathVariable("emailId")String emailId,@RequestBody @Valid ResetPassword resetPassword){
//		return userService.resetPassword(emailId,resetPassword);
//	}
	
//}

}
