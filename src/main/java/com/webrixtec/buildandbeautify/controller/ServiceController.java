package com.webrixtec.buildandbeautify.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webrixtec.buildandbeautify.exception.ExceptionController;
import com.webrixtec.buildandbeautify.model.ServiceRequest;
import com.webrixtec.buildandbeautify.pojo.clientServicePojo;
import com.webrixtec.buildandbeautify.pojo.clientServiceUpdatePojo;
import com.webrixtec.buildandbeautify.repo.clientServiceRepo;
import com.webrixtec.buildandbeautify.service.Request;

@RestController
@CrossOrigin
public class ServiceController extends ExceptionController {
	@Autowired
	Request LCS;
	@Autowired
	clientServiceRepo CSR;
	
	@PostMapping(value = "/create-client-service")
	public ResponseEntity<Object> createService(@Valid clientServicePojo Request) throws Exception {
		return LCS.createService(Request);
	}
	@PostMapping(value = "/update-client-service")
	public ResponseEntity<Object> updateService(@Valid clientServiceUpdatePojo Request) throws Exception {
		return LCS.updateService(Request);
	}
	
	@GetMapping(value = "/get-client-service")
	public ResponseEntity<Object> getService() throws Exception {
		return LCS.getService();
	}
	@GetMapping(value = "/getServicenotify")
	public ResponseEntity<Object> getnotify() throws Exception {
		return LCS.getServicenotify();
	}
	@GetMapping(value ="/delSerivcenotify")
	public ResponseEntity<Object> delnotify(){
		return LCS.delSerivcenotify();
	}
}
