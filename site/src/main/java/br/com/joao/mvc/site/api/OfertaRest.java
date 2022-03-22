package br.com.joao.mvc.site.api;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.mvc.site.dto.RequisicaoNovaOferta;
import br.com.joao.mvc.site.model.Oferta;
import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.repository.PedidoRepository;
import br.com.joao.mvc.site.repository.UserRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaRest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	

	
	
	@PostMapping
	public Oferta createOffer(@Valid @RequestBody
			RequisicaoNovaOferta requisicao, Principal principal) {
		Optional<Pedido> pedidoLista = pedidoRepository.findById(requisicao.getPedidoId());
		
		if(!pedidoLista.isPresent()) {
			return null;
		}
		
		Pedido pedido = pedidoLista.get();
		
		
		
		Oferta novaOferta = requisicao.toOferta();
		novaOferta.setPedido(pedido);
		novaOferta.setUsuario(userRepository.findByUsername(principal.getName()));
		pedido.getOfertas().add(novaOferta);
		pedidoRepository.save(pedido);
		
		return novaOferta;
	}
	

	
	
	
	
	
}
