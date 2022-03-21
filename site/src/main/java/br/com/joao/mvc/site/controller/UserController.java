package br.com.joao.mvc.site.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.repository.PedidoRepository;

@Controller
@RequestMapping("usuario")
public class UserController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	
	@GetMapping("pedido")
	public String home(Model model, Principal principal) {
		
		
		
		Sort sort = Sort.by("id");
		
		PageRequest pagina = PageRequest.of(0, 10, sort);
		
		List<Pedido> pedidos = pedidoRepository.findAllByUserPageable(principal.getName(), pagina);
		
		
		model.addAttribute("pedidos", pedidos);
		
		
		
		return "usuario/home";
	}
	
	@GetMapping("pedido/pagina")
	public String findPage( Model model, Principal principal) {
		
		
		Sort sort = Sort.by("id");
			
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		
		
		List<Pedido> pedidos = pedidoRepository.findAllByUserPageable(principal.getName(), paginacao);
		
	
		
		model.addAttribute("pedidos", pedidos);
		
		
		
		return "usuario/home";
	}
	
	
	
	@GetMapping("pedido/{status}")
	public String status( @PathVariable("status") String status, Model model, Principal principal) {
		
		Sort sort = Sort.by("id");
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		List<Pedido> todosPedidos = pedidoRepository.findByStatusAndUser(status.toUpperCase(), principal.getName());
		List<Pedido> pedidos = pedidoRepository.findByStatusAndUserPageable(status.toUpperCase(), principal.getName(), paginacao);
		
		int totalPaginas = todosPedidos.size()/2 + 1;
		
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		model.addAttribute("totalPaginas", totalPaginas);
		
		
		return "usuario/home";
	}
	
	
	
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		
		return "redirect:/usuario/home";
		
	}
	
}
