package br.com.joao.mvc.site.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.repository.PedidoRepository;

@Service
public class PedidoService {
	
	private PedidoRepository pedidoRepository;
		
	public Page<Pedido> findPaginated(int pageNumber, int pageSize){
		
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		
		return this.pedidoRepository.findAll(pageable);
		
		
	}
	
}
