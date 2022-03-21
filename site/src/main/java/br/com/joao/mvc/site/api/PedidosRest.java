
package br.com.joao.mvc.site.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.model.StatusPedido;
import br.com.joao.mvc.site.repository.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	

	@GetMapping("aguardando")
	public List<Pedido> getPedidosWithoutOffers(Principal principal){
		
		Sort sort = Sort.by("id");
		PageRequest pagina = PageRequest.of(0, 10, sort);
	
		
		return pedidoRepository.findByStatusAndUserNotEqual(StatusPedido.AGUARDANDO.name(), principal.getName(), pagina);
		
	}
	
}
