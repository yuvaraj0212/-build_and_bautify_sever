package com.webrixtec.buildandbeautify.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webrixtec.buildandbeautify.exception.ExceptionController;
import com.webrixtec.buildandbeautify.model.UserModel;
import com.webrixtec.buildandbeautify.pojo.LoginRequest;
import com.webrixtec.buildandbeautify.pojo.ResetPassword;
import com.webrixtec.buildandbeautify.repo.UserRepo;
import com.webrixtec.buildandbeautify.util.JwtUtils;



@Service
public class UserService extends ExceptionController {

	@Autowired(required=true)
	private AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	UserRepo userRepo;
	@Autowired
	private JavaMailSender mailSender;
	
	
	
	public ResponseEntity<Object> signin(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		UserModel user = userRepo.findById(userDetails.getId()).orElseThrow(() -> new RuntimeException("user not found"));
		user.setToken(jwt);
		return response(HttpStatus.OK.value(), " login successfully", user);
	}

	public ResponseEntity<Object> signUp(UserModel userModel) throws MessagingException {
		UserModel userDetails = new UserModel();
		boolean emailExists = userRepo.existsByEmail(userModel.getEmail());
		if (emailExists) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Email ID already exists");
		}
		if(!userModel.getPassword().equals(userModel.getConfirmpassword())) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"password and confirm password not matched please check");
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encode = encoder.encode(userModel.getPassword());
		userDetails.setPassword(encode);
		userDetails.setName(userModel.getName());
		userDetails.setConfirmpassword(encode);
		userDetails.setPhone(userModel.getPhone());
		userDetails.setEmail(userModel.getEmail());
		userDetails.setAdharNo(userModel.getAdharNo());
		userDetails.setDob(userModel.getDob());
		userDetails.setEmpId(userModel.getEmpId());
		userDetails.setGender(userModel.getGender());
		userDetails.setRolename(userModel.getRolename());
//		userDetails.setCreatedDate(new Date());
//		userDetails.setModifiedDate(new Date());
//		Set<Role> role = roleRepo.findByRole("user");
//		userDetails.setRoles(role);
		userRepo.save(userDetails);
		mailSender(userDetails.getEmail(),"Welcome ",userDetails.getName());
		return response(HttpStatus.OK.value(), "user created successfully", userDetails);
	}

	public void mailSender(String emailId,String subject ,String name) throws MessagingException {
//		SimpleMailMessage email = new SimpleMailMessage();
//		email.setFrom("BuildAndBeautify <webrixtec@gamil.com>");
//		email.setSubject(subject);
//		email.setText("Hi"+ name +'\n'
//				+ " thanks for choosing Srinivasaka Enterprises.  ");
//		email.setTo(emailId);
//		mailSender.send(email);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "Hi "+ name +"<br /> "+
				" Thanks for choosing<b> Srinivasaka Enterprises.</b><br /> "
				+ "<img src='http://3.110.219.120/images/logo.png'/>";
		helper.setText(htmlMsg, true); // Use this or above line.
		helper.setTo(emailId);
		helper.setSubject(subject);
		helper.setFrom("BuildAndBeautify <webrixtec@gamil.com>");
		mailSender.send(mimeMessage);
	}

	public ResponseEntity<Object> getUserList() {
		List<UserModel> userList = userRepo.findAll();
		return response(HttpStatus.OK.value(), "userlist", userList);
	}


//	public ResponseEntity<Object> updateUser(UserModel userModel) {
//		UserModel userDetails = userRepo.findById(userModel.getId()).get();
//		if (userDetails == null) {
//			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
//					"User details are not avalaible");
//		}
//		userDetails.setPhone(userModel.getPhone());
//		userDetails.setName(userModel.getName());
//		userRepo.save(userDetails);
//		return response(HttpStatus.OK.value(), "user details updated successfully", userDetails);
//	}
//	
	public ResponseEntity<Object> forgotPassword(String emailId) throws MessagingException{
		UserModel userDetails = userRepo.findByEmail(emailId);
		if(userDetails == null) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"User details are not avalaible");
		}
//		mailSender(userDetails.getEmail(),"forgot password link",userDetails.getName());
			
		 MimeMessage mimeMessage  = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "Hi "+ userDetails.getName() +"<br /> "+
				" thanks for choosing<b> Srinivasaka Enterprises.</b>"+"<br /> <a href=http://3.110.219.120/resetpassword:"+userDetails.getEmail()+
				">click the link to change the password</a>";
				
				
		helper.setText(htmlMsg, true); 
		helper.setTo(userDetails.getEmail());
		helper.setSubject("forgot password link");
		mailSender.send(mimeMessage);
		return response(HttpStatus.OK.value(),"reset password link has been to sent to your registered mail");
	}

	public ResponseEntity<Object> resetPassword(String emailId,ResetPassword resetPassword){
		UserModel userDetails = userRepo.findByEmail(emailId);
		if(userDetails == null) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"User details are not avalaible");
		}
		if(!resetPassword.getPassword().equals(resetPassword.getConfirmPassword())) {
			return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"password and confirm password not matched please check");
		}
		
		BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder();
		userDetails.setPassword(cryptPassword.encode(resetPassword.getPassword()));
		userDetails.setConfirmpassword(cryptPassword.encode(resetPassword.getConfirmPassword()));
		userRepo.save(userDetails);
		return response(HttpStatus.OK.value(),"Password updated successfully");
	}
	

	

	
	
	
}
