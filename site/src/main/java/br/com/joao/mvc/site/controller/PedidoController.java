package br.com.joao.mvc.site.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joao.mvc.site.dto.RequisicaoNovoPedido;
import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.model.Usuario;
import br.com.joao.mvc.site.repository.PedidoRepository;
import br.com.joao.mvc.site.repository.UserRepository;



@Controller
@RequestMapping("pedidos")
public class PedidoController {
	
	
	
	@Autowired
	PedidoRepository pedidoRepository;
	Pedido pedido = new Pedido();
	
	@Autowired
	UserRepository userRepository;
	Usuario user = new Usuario();
	
	
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedidos/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		
		if(result.hasErrors()) {
			return "pedidos/formulario";
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario user = userRepository.findByUsername(username);
		
		
		Pedido pedido = requisicao.toPedido();
		pedido.setUser(user);
		pedidoRepository.save(pedido);
		
		return"redirect:/home";		
		
	}
	
	
	
	
	
} 

