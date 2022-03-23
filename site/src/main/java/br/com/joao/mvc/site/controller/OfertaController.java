package br.com.joao.mvc.site.controller;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joao.mvc.site.model.Oferta;
import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.model.StatusOferta;
import br.com.joao.mvc.site.model.StatusPedido;
import br.com.joao.mvc.site.repository.OfertaRepository;
import br.com.joao.mvc.site.repository.PedidoRepository;

@Controller
@RequestMapping("/oferta")
public class OfertaController {
	
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public String getFormOffers() {
		return "oferta/home";
	}
	
	@GetMapping("ofertasRecebidas")
	public String getReceiveOffers( Model model, Principal principal) {
		
		List<Oferta> ofertas = ofertaRepository.findByPedidoUser(principal.getName());
		
		model.addAttribute("ofertas", ofertas);
		
		
		return "oferta/ofertasRecebidas";
	}
	
	
	@GetMapping("ofertasRecebidas/{status}")
	public String getReceiveOffers(@PathVariable("status") String status, Model model, Principal principal) {
		
		List<Oferta> ofertas = ofertaRepository.findByPedidoUserAndStatus(principal.getName(), status.toUpperCase());
		
		model.addAttribute("ofertas", ofertas);
		model.addAttribute("status", status);
		
		return "oferta/ofertasRecebidas";
	}
	
	
	
	@GetMapping("rejeitaOferta/{idOferta}")
	public String rejectOffer(@PathVariable("idOferta") Long idOferta) {
		
		
		List<Oferta> ofertas = ofertaRepository.encontrarPorId(idOferta);
		for(Oferta oferta : ofertas) {
			
			oferta.setStatus(StatusOferta.REJEITADA);
			ofertaRepository.save(oferta);
			
		}
		
		return "redirect:/oferta/ofertasRecebidas";
		
		
	}
	
	@GetMapping("aceitaOferta/{idOferta}")
	public String acceptOffer(@PathVariable("idOferta") Long idOferta) {
		
		
		List<Oferta> ofertas = ofertaRepository.encontrarPorId(idOferta);
		for(Oferta oferta : ofertas) {
			
			oferta.setStatus(StatusOferta.ACEITA);
			ofertaRepository.save(oferta);
			
			Long idPedido = oferta.getPedido().getId();
			Pedido pedido = pedidoRepository.encontraPorId(idPedido);
			pedido.setStatus(StatusPedido.APROVADO);
			pedido.setValor(oferta.getValor());
			pedido.setDataEntrega(oferta.getDataEntrega());
			
			
			pedidoRepository.save(pedido);
	
			
		}
		
		return "redirect:/oferta/ofertasRecebidas";
		
		
	}
	
	@GetMapping("ofertasEnviadas")
	public String MyOffers(Model model, Principal principal) {
		
		List<Oferta> minhasOfertas = ofertaRepository.findByUsuario(principal.getName());
		
		model.addAttribute("minhasOfertas", minhasOfertas);
		
		return "oferta/ofertasEnviadas";
		
		
	}
	
	@GetMapping("ofertasEnviadas/{status}")
	public String MyOffers(@PathVariable("status") String status, Model model, Principal principal) {
		
		List<Oferta> ofertas = ofertaRepository.findByUsuarioAndStatus(principal.getName(), status.toUpperCase());
		
		
		
		model.addAttribute("status", status);
		model.addAttribute("minhasOfertas", ofertas);
		
		return "oferta/ofertasEnviadas";
		
	}
	

	@Transactional
	@GetMapping("deletaOferta/{idOferta}")
	public String DeleteOffer(@PathVariable(value = "idOferta") Long idOferta) {
		
		ofertaRepository.deletaPorId(idOferta);
	
		return "redirect:/oferta/ofertasEnviadas";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
