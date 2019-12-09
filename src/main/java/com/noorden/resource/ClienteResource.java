package com.noorden.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noorden.model.Cliente;
import com.noorden.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		
		List<Cliente> clientes = clienteService.listar();
		
		return clientes != null ? ResponseEntity.status(HttpStatus.OK).body(clientes) 
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> buscarPorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.buscarPorId(id);
		
		return cliente != null ? ResponseEntity.status(HttpStatus.OK).body(cliente)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Cliente cliente) {
		
		cliente = clienteService.salvar(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> atualizar(@RequestBody Cliente cliente, 
			@PathVariable("id") Long id) {
		
		cliente.setId(id);
		clienteService.atualizar(cliente);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		clienteService.deletar(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
