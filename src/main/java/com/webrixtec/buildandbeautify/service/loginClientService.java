package com.webrixtec.buildandbeautify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webrixtec.buildandbeautify.exception.ExceptionController;
import com.webrixtec.buildandbeautify.model.loginCleintModel;
import com.webrixtec.buildandbeautify.repo.LoginClientRepo;

@Service
public class loginClientService extends ExceptionController{
	
	@Autowired
	LoginClientRepo LCR;
	
	
	public ResponseEntity<Object> createEnquiry(loginCleintModel productRequest) {
		loginCleintModel LCM = new loginCleintModel();
		LCM.setComment(productRequest.getComment());
		LCM.setComplaint(productRequest.getComplaint());
		LCM.setEnquiry(productRequest.getEnquiry());
		LCM.setOther(productRequest.getOther());
		LCR.save(LCM);
		return response(HttpStatus.OK.value(), "Enquiry added Succcessfully", LCM);
	}

}
