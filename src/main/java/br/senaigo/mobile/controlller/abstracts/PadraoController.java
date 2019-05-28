package br.senaigo.mobile.controlller.abstracts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.senaigo.mobile.interfaces.GenericOperationsController;

public abstract class PadraoController<E> implements GenericOperationsController<E>{
	
	private Logger logger;
	
	@Override
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public HeadersBuilder<?> put(@RequestBody E objeto) {
		try {
			getGenericOperations().put(objeto);
			getLogger().info(String.format("Registro atualizado: %s", objeto.toString()));
			return ResponseEntity.noContent();
		} catch (Exception e) {
			getLogger().error(String.format("Erro ao executar o método PUT.\nMensagem: %s",e.getMessage()));
			return ResponseEntity.badRequest();
		}
	}

	@Override
	@DeleteMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public HeadersBuilder<?> delete(@RequestBody E objetos) {
		try {
			getGenericOperations().delete(objetos);
			getLogger().info(String.format("Registro(s) deletado(s): %s",objetos.toString()));
			return ResponseEntity.noContent();
		} catch (Exception e) {
			getLogger().error(String.format("Erro ao executar o método PUT.\nMensagem: %s",e.getMessage()));
			return ResponseEntity.badRequest();
		}

	}

	@Override
	@PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public HeadersBuilder<?> patch(@RequestBody E objeto) {
		try {
			getGenericOperations().patch(objeto);
			getLogger().info(String.format("Registro atualizado: %s",objeto.toString()));
			return ResponseEntity.noContent();
		} catch (Exception e) {
			getLogger().error(String.format("Erro ao executar o método PATCH.\nMensagem: %s",e.getMessage()));
			return ResponseEntity.badRequest();
		}

	}
	
	@Override
	public Logger getLogger() {
		if(logger == null) {
			logger = LoggerFactory.getLogger(getClassController());
		}
		return logger;
	}
}
