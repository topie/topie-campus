package com.topie.campus.core.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/admin")
	public String adminLogin()
	{
		return "login";
	}

}
