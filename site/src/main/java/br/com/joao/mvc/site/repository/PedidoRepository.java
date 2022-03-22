package br.com.joao.mvc.site.repository;



import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.model.StatusPedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	
	@Query(value = "SELECT * FROM pedidos p INNER JOIN users u ON u.username = p.nome_usuario WHERE p.nome_usuario = "
			+ ":nomeUsuario AND p.status = :status ORDER BY p.valor", nativeQuery = true)
	List<Pedido> findByStatusAndUser(String status, String nomeUsuario);
	
	
	
	@Query(value = "SELECT * FROM pedidos p INNER JOIN users u ON u.username = p.nome_usuario WHERE p.nome_usuario = :nomeUsuario ORDER BY p.valor", nativeQuery = true)
	List<Pedido> findAllByUser(String nomeUsuario);
	
	
	@Query(value = "SELECT * FROM pedidos p INNER JOIN users u ON u.username = p.nome_usuario WHERE p.nome_usuario = :nomeUsuario ORDER BY p.valor", nativeQuery = true)
	List<Pedido> findAllByUserPageable(String nomeUsuario, Pageable pagina);

	
	List<Pedido> findByStatus(StatusPedido entregue, Pageable pagina);
	
	@Query(value = "SELECT * FROM pedidos p WHERE p.id = :id", nativeQuery = true)
	Pedido encontraPorId(Long id);
	
	
	@Query(value = "SELECT * FROM pedidos p INNER JOIN users u ON u.username = p.nome_usuario WHERE p.nome_usuario != :username AND p.status = :status", nativeQuery = true)
	List<Pedido> findByStatusAndUserNotEqual(String status, String username, Pageable pageable);
	
	
	
	
	
	
	
}
