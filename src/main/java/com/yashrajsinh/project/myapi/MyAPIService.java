package com.yashrajsinh.project.myapi;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.yashrajsinh.project.restapi.RestAPIService;

@Service
public class MyAPIService {
	
	@Autowired
	private RestAPIService restApiService;
	
	@Value("{my.api}")
	private String myApi;
	
	public Object mypassedValues(String yourValue1) throws JsonParseException, JsonMappingException, RestClientException, URISyntaxException, IOException {
		
		String updatedAPI = myApi.replace("{yourValue1}", yourValue1);
		
		//here you can also use hashmap and put method of hasmap to put values and pass your hashmap instead of null
		
		return restApiService.getResultAsJsonNode(updatedAPI, null, "GET");
	}
	

}
