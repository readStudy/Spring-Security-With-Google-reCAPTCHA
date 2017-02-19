package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.recaptcha.RecaptchaFilter;

@EnableWebSecurity 
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private RecaptchaFilter recaptchaFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			/*
			 * The RecaptchaFilter need to add before UsernamePasswordAuthenticationFilter,
			 * so that it can filter the login request.
			 * Reference: 6.3.6 Adding in Your Own Filters.
			 *            http://docs.spring.io/spring-security/site/docs/4.2.2.BUILD-SNAPSHOT/reference/htmlsingle/#ns-custom-filters
			 */
		  	.addFilterBefore(recaptchaFilter, UsernamePasswordAuthenticationFilter.class)				
		  	.formLogin().loginPage("/login").defaultSuccessUrl("/welcome");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication() 
			.withUser("user").password("password").roles("USER");
	}
	

}
