package com.yashrajsinh.project.myapi;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class MyAPIController {
	
	@Autowired
	private MyAPIService myApiService;
	
	@GetMapping("/myApi")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getMyApi(@RequestParam("yourValue1")String yourValue1) throws JsonParseException, JsonMappingException, RestClientException, URISyntaxException, IOException{
		
		Object list = null;
		
		list = myApiService.mypassedValues(yourValue1);
				
		return new ResponseEntity<Object>(list,HttpStatus.OK);
	}

}
