package br.com.joao.mvc.site.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.mvc.site.interceptor.InterceptadorDeAcessos;
import br.com.joao.mvc.site.interceptor.InterceptadorDeAcessos.Acesso;

@RequestMapping("acessos")
@RestController
public class AcessosRest {
	
	public List<Acesso> getAcessos(){	
		return InterceptadorDeAcessos.acessos;
	}
	
}
