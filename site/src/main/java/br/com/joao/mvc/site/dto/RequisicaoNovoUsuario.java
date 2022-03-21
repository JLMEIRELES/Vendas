package br.com.joao.mvc.site.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class RequisicaoNovoUsuario {
	
	
	
	@NotBlank
	String nome;
	@NotBlank
	String password;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public UserDetails toUser() {	
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return User.builder().username(nome).password(encoder.encode(password)).roles("USER").build();
		
	}
	
	
}
