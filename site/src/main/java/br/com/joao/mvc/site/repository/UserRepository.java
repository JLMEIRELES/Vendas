package br.com.joao.mvc.site.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.joao.mvc.site.model.Usuario;

@Repository
public interface UserRepository extends CrudRepository<Usuario, String> {
	@Cacheable("usuarios")
	Usuario findByUsername(String username);
	
}
