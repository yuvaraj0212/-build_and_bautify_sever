package com.webrixtec.buildandbeautify.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webrixtec.buildandbeautify.exception.ExceptionController;
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
	public ResponseEntity<Object> createService(@Valid clientServicePojo Request) throws Exception {
		return LCS.createService(Request);
	}
	
	@GetMapping(value = "/get-client-service")
	public ResponseEntity<Object> getService() throws Exception {
		return LCS.getService();
	}
}
