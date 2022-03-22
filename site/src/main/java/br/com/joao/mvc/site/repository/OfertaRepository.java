package br.com.joao.mvc.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import br.com.joao.mvc.site.model.Oferta;

@Repository
public interface OfertaRepository extends CrudRepository<Oferta, Long> {

	@Query(value = "SELECT * FROM ofertas o INNER JOIN pedidos p ON p.id = o.pedido_id WHERE p.nome_usuario = :username AND o.status = :status", nativeQuery = true )
	List<Oferta> findByPedidoUserAndStatus(String username, String status);
	
	@Query(value = "SELECT * FROM ofertas o WHERE id= :id", nativeQuery = true)
	List<Oferta> encontrarPorId(Long id);
	
	@Query(value = "SELECT * FROM ofertas o INNER JOIN pedidos p ON p.id = o.pedido_id WHERE p.nome_usuario = :username", nativeQuery = true )
	List<Oferta> findByPedidoUser(String username);
	
	
	Long findByPedido(Long idPedido);
	
	@Query(value = "SELECT * FROM ofertas WHERE usuario_username = :name", nativeQuery = true)
	List<Oferta> findByUsuario(String name);
	
	@Query(value = "SELECT * FROM ofertas WHERE usuario_username = :name AND status = :status", nativeQuery = true)
	List<Oferta> findByUsuarioAndStatus(String name, String status);
	
	@Modifying
	@Query(value = "DELETE FROM ofertas WHERE id = :id", nativeQuery = true)
	void deletaPorId(Long id);
}
