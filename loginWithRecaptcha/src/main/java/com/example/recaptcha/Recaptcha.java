package com.example.recaptcha;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Recaptcha {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${google.recaptcha.url}")
	private String recaptchaUrl;

	@Value("${google.recaptcha.secret-key}")
	private String recaptchaSecretKey;

	public RecaptchaResult getResult(String remoteIp, String response) {

		return restTemplate.postForEntity(recaptchaUrl, createRequest(recaptchaSecretKey, remoteIp, response),
				RecaptchaResult.class).getBody();
	}

	private MultiValueMap<String, String> createRequest(String secret, String remoteIp, String response) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("secret", secret);
		map.add("remoteip", remoteIp);
		map.add("response", response);
		return map;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class RecaptchaResult {
		@JsonProperty("success")
		private boolean success;
		@JsonProperty("error-codes")
		private ArrayList<String> errorCodes;
		@JsonProperty("challenge_ts")
		private String challegeTs;
		@JsonProperty("hostname")
		private String hostName;

		public boolean isSuccess() {
			return success;
		}

		public ArrayList<String> getErrorCodes() {
			return errorCodes;
		}

		public String getChallegeTs() {
			return challegeTs;
		}

		public String getHostName() {
			return hostName;
		}
	}

}