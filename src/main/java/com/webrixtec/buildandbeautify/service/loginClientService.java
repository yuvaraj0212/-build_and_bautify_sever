package com.webrixtec.buildandbeautify.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webrixtec.buildandbeautify.exception.ExceptionController;
import com.webrixtec.buildandbeautify.model.UserModel;
import com.webrixtec.buildandbeautify.model.loginCleintModel;
import com.webrixtec.buildandbeautify.pojo.clientdashboardRequest;
import com.webrixtec.buildandbeautify.repo.LoginClientRepo;
import com.webrixtec.buildandbeautify.repo.UserRepo;

@Service
public class loginClientService extends ExceptionController{
	
	@Autowired
	LoginClientRepo LCR;
	@Autowired
	UserRepo userRepo; 
	
	public ResponseEntity<Object> createEnquiry(clientdashboardRequest productRequest) {
		UserModel user = userRepo.findById(productRequest.getClientId()).get();
		loginCleintModel LCM = new loginCleintModel();
		LCM.setUserModel(user);
		LCM.setComment(productRequest.getComment());
		LCM.setComplaint(productRequest.getComplaint());
		LCM.setProduct(productRequest.getProduct());
		LCM.setCategory(productRequest.getCategory());
		LCM.setQuantity(productRequest.getQuantity());
		LCM.setOther(productRequest.getOther());
		LCR.save(LCM);
		return response(HttpStatus.OK.value(), "Enquiry added Succcessfully", LCM);
	}

	public ResponseEntity<Object> getEnquiry() {
		List<loginCleintModel> enquirys = LCR.findAll();
		Collections.reverse( enquirys);
		return response(HttpStatus.OK.value(), "enquirys list", enquirys); 
	}

}
