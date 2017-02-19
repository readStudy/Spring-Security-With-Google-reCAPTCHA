package com.example.recaptcha;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.example.recaptcha.Recaptcha.RecaptchaResult;

@Component
public class RecaptchaFilter extends GenericFilterBean {

	@Autowired
	private Recaptcha recaptcha;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String recaptchaResponse = request.getParameter("g-recaptcha-response");

		if (isPostRequest(request) && recaptchaResponse != null) {

			// I didn't use remoteip here, because it is optional,
			// Reference: https://developers.google.com/recaptcha/docs/verify
			RecaptchaResult result = recaptcha.getResult("", recaptchaResponse);

			if (!result.isSuccess()) {

				logger.info(result.getErrorCodes());

				response.sendRedirect(request.getRequestURI() + "?error=incorrectRecaptcha");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean isPostRequest(HttpServletRequest request) {
		return request.getMethod().equalsIgnoreCase("POST");
	}
}
