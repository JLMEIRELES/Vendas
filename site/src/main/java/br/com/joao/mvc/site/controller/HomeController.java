package br.com.joao.mvc.site.controller;

import java.security.Principal;
import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joao.mvc.site.dto.RequisicaoNovoUsuario;
import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.model.StatusPedido;
import br.com.joao.mvc.site.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private DataSource dataSource;

	@GetMapping
	public String home(Model model, Principal principal) {

		Sort sort = Sort.by("id");
		PageRequest pagina = PageRequest.of(0, 10, sort);

		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, pagina);

		model.addAttribute("pedidos", pedidos);

		return "home";
	}

	@GetMapping("/{status}")
	public String status(@PathVariable("status") String status, Model model, Principal principal) {
		
		Sort sort = Sort.by("id");
		PageRequest  pagina = PageRequest.of(0, 0, sort);
		List<Pedido> pedidos = pedidoRepository.findByStatusAndUser(status.toUpperCase(), principal.getName());

		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);

		return "home";
	}

	@GetMapping("cadastro")
	public String cadastro(RequisicaoNovoUsuario requisicao) {

		return "cadastro";

	}

	@PostMapping("novoUsuario")
	public String novoUsuario(@Valid RequisicaoNovoUsuario requisicao, BindingResult result) {

		if (result.hasErrors()) {
			return "home/cadastro";
		}

		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.createUser(requisicao.toUser());

		return "redirect:/home";

	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {

		return "redirect:/home";

	}

}
