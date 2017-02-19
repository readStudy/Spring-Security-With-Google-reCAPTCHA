package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String displayLogin() {
		return "login";
	}

    @ModelAttribute("recaptchaSiteKey")
    public String getRecaptchaSiteKey(@Value("${google.recaptcha.site-key}") String recaptchaSiteKey) {
        return recaptchaSiteKey;
    }
}
