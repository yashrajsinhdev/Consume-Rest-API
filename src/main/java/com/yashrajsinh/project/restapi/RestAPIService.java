package com.yashrajsinh.project.restapi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class RestAPIService {
	
	@Value("${my.host}")
	private String host;

	public JsonNode getResultAsJsonNode(String updatedAPI, Map<String, Object> params, String method)
			throws RestClientException, URISyntaxException, JsonParseException, JsonMappingException, IOException {

		RestTemplate restTemplate = new RestTemplate();;
		
		ResponseEntity<JsonNode> result = null;
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, Object>> entity = null;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + updatedAPI);

		if (params != null) {
			entity = new HttpEntity<Map<String, Object>>(params, header);
			for (Entry<String, Object> entry : params.entrySet()) {
				builder.queryParam(entry.getKey(), entry.getValue());
			}

		} 

		try {

			switch (method.toUpperCase()) {
			case "POST":
				result = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, entity, JsonNode.class);
				break;

			case "GET":
				result = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, JsonNode.class);
				break;
			}
			return result.getBody();
		} catch (HttpStatusCodeException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getResponseBodyAsString());
		}
	}

}
