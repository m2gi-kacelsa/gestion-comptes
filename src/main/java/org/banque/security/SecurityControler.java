package org.banque.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityControler {

	@RequestMapping(value = "/login")
	public String login() {
		return "/login";
	}

	@RequestMapping(value = "/")
	public String home() {
		return "redirect:/operations";
	}

}
