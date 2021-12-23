package com.webrixtec.buildandbeautify.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ExceptionController;
import com.webrixtec.buildandbeautify.pojo.clientServicePojo;
import com.webrixtec.buildandbeautify.repo.clientServiceRepo;
import com.webrixtec.buildandbeautify.service.loginClientService;

@RestController
@CrossOrigin
public class clientServiceController extends ExceptionController {
	@Autowired
	loginClientService LCS;
	@Autowired
	clientServiceRepo CSR;
	
	@PostMapping(value = "/create-client-service")
	public ResponseEntity<Object> createProduct(@Valid clientServicePojo Request) throws Exception {
		return LCS.createService(Request);
	}
}
