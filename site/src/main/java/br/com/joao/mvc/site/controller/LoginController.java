package br.com.joao.mvc.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joao.mvc.site.dto.RequisicaoNovoUsuario;



@Controller
@RequestMapping("login")
public class LoginController {
	
	@GetMapping
	public String login() {
		
		return "login";
		
	}
	
	

	
}
