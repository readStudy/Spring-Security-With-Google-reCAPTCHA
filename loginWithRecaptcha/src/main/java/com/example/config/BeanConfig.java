package com.example.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.recaptcha.RecaptchaFilter;

@Configuration
public class BeanConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	/*
	 * Any Servlet or Filter beans will be registered with the servlet container automatically.
	 * To disable registration of a particular Filter bean create a registration bean for it and mark it as disabled. 
	 * Reference: http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-disable-registration-of-a-servlet-or-filter
	 * */
	@Bean
	public FilterRegistrationBean registration(RecaptchaFilter filter) {
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter);
	    registration.setEnabled(false);
	    return registration;
	}
}
