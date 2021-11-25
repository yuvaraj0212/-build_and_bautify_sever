package com.webrixtec.buildandbeautify.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webrixtec.buildandbeautify.exception.ExceptionController;
import com.webrixtec.buildandbeautify.model.clientModel;
import com.webrixtec.buildandbeautify.repo.clientRepo;
import com.webrixtec.buildandbeautify.service.clientService;




@RestController
@CrossOrigin
public class dashboardController extends ExceptionController {

	@Autowired
	clientRepo clientRepo;
	@Autowired
	clientService clientService;
	

	@PostMapping(value = "/create-client")
	public ResponseEntity<Object> createclient(@RequestBody clientModel productRequest) throws Exception {
		return clientService.createClient(productRequest);
	}

	@PostMapping(value = "/update-client")
	public ResponseEntity<Object> editClient(@RequestBody clientModel productRequest) throws IOException {
		return clientService.updateclient(productRequest);
	}

	@GetMapping(value = "/get-client")
	public ResponseEntity<Object> productDetails(){
		return clientService.getProductList();
	}

	@DeleteMapping(value = "/delete-client")
	public ResponseEntity<Object> deleteClient(@RequestParam(name = "clientId") Long ClientId) {
		return clientService.deleteClient(ClientId);
	}
	

}
