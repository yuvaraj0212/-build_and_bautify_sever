package com.webrixtec.buildandbeautify.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.webrixtec.buildandbeautify.exception.ExceptionController;
import com.webrixtec.buildandbeautify.model.ClientServiceModel;
import com.webrixtec.buildandbeautify.model.UserModel;
import com.webrixtec.buildandbeautify.model.loginCleintModel;
import com.webrixtec.buildandbeautify.pojo.clientServicePojo;
import com.webrixtec.buildandbeautify.pojo.clientdashboardRequest;
import com.webrixtec.buildandbeautify.repo.LoginClientRepo;
import com.webrixtec.buildandbeautify.repo.UserRepo;
import com.webrixtec.buildandbeautify.repo.clientServiceRepo;
import com.twilio.type.PhoneNumber;

@Service
public class loginClientService extends ExceptionController{
	
	@Autowired
	LoginClientRepo LCR;
	@Autowired
	clientServiceRepo CSR;
	@Autowired
	UserRepo userRepo; 
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	FileStorageService FileStorageService;
	
	public static final String ACCOUNT_SID = "AC9706cc99cfa46154daf86cf479efe8bf";
	public static final String AUTH_TOKEN = "0cce23c115d62e3ca34665c9f16bdcdb";
	int notifycation = 0;
	public ResponseEntity<Object> createEnquiry(clientdashboardRequest productRequest) throws MessagingException {
		
		UserModel user = userRepo.findById(productRequest.getClientId()).get();
		loginCleintModel LCM = new loginCleintModel();
		LCM.setUserModel(user);
		LCM.setComment(productRequest.getComment());
		LCM.setProduct2(productRequest.getProduct2());
		LCM.setProduct(productRequest.getProduct());
		ArrayList<String> categoryList = new ArrayList<String>();
		for(int i = 0;i < productRequest.getCategory().size(); i++) {
			categoryList.add(productRequest.getCategory().get(i)+",");
			System.out.println(productRequest.getCategory().get(i));
		}
		
		LCM.setCategory(categoryList);
		System.out.println(categoryList);
		LCM.setQuantity(productRequest.getQuantity());
		LCM.setOther(productRequest.getOther());
		LCM.setCreateDate(new Date());
		LCM.setModifiedDate(new Date());
		notifycation+=1;
		LCR.save(LCM);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "Hi "+ user.getName() +"<br /> "+
				" Thanks for choosing<b> Srinivasaka Enterprises.</b><br /> "
				+ "<img src='http://3.110.219.120/images/logo.png'/>";
		helper.setText(htmlMsg, true); // Use this or above line.
		helper.setTo(user.getEmail());
		helper.setSubject("your Enqurie added succesFully");
		helper.setFrom("BuildAndBeautify <webrixtec@gamil.com>");
		mailSender.send(mimeMessage);
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+91"+user.getPhone()),
                new com.twilio.type.PhoneNumber("+17279105894"),
                "your Enqurie added succesFully.Thanks for choosing Srinivasaka Enterprises.")
            .create();

        System.out.println(message);
//    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message1 = Message.creator(
//        		   new com.twilio.type.PhoneNumber("whatsapp:+917092327466"), 
//                   new com.twilio.type.PhoneNumber("whatsapp:+17279105894"),  
//                   "Your appointment is coming up on July 21 at 3PM")
//            .create();
//        System.out.println(message1.getSid());
		return response(HttpStatus.OK.value(), "Enquiry added Succcessfully", LCM);
	}

	public ResponseEntity<Object> getEnquiry() {
		List<loginCleintModel> enquirys = LCR.findAll();
		Collections.reverse( enquirys);
		return response(HttpStatus.OK.value(), "enquirys list", enquirys); 
	}

	public ResponseEntity<Object> getnotify() {
		return response(HttpStatus.OK.value(), "notify No:", notifycation);
	}

	public ResponseEntity<Object> delnotify() {
		System.out.println(notifycation	);
		notifycation = 0;
		System.out.println(notifycation	);
		return response(HttpStatus.OK.value(), "notify No:", notifycation);
	}

	public ResponseEntity<Object> createService( clientServicePojo request) {
		ClientServiceModel serviceModel = new ClientServiceModel();
		if (request.getMfile() == null) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"File must not empty");
		}
		UserModel user = userRepo.findById(request.getClientId()).get();
		serviceModel.setUserModel(user);
		serviceModel.setBillno(request.getBillno());
		serviceModel.setComment(request.getComment());
		serviceModel.setProductName(request.getProductName());
		serviceModel.setService(request.getService());
		serviceModel.setFilename(request.getMfile().getOriginalFilename());
		serviceModel.setImageURL(FileStorageService.uploadFile(request.getMfile(),"bill"));
		serviceModel.setCreateDate(new Date());
		serviceModel.setModifiedDate(new Date());
		CSR.save(serviceModel);
		return response(HttpStatus.OK.value(), "Request added Succcessfully", serviceModel);
		
	}

}
