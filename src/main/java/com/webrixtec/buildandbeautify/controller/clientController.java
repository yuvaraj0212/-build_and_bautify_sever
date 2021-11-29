package com.webrixtec.buildandbeautify.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webrixtec.buildandbeautify.exception.ExceptionController;
import com.webrixtec.buildandbeautify.model.clientModel;
import com.webrixtec.buildandbeautify.model.loginCleintModel;
import com.webrixtec.buildandbeautify.pojo.clientdashboardRequest;
import com.webrixtec.buildandbeautify.service.loginClientService;

@RestController
@CrossOrigin
public class clientController extends ExceptionController {
	
	@Autowired
	loginClientService LCS;
	
	@PostMapping(value = "/create-client-enquiry")
	public ResponseEntity<Object> createEnquiry(@Valid @RequestBody clientdashboardRequest productRequest) throws Exception {
		return LCS.createEnquiry(productRequest);
	}
	
}
