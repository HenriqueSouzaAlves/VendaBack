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

import com.noorden.model.Venda;
import com.noorden.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaResource {

	@Autowired
	private VendaService vendaService;
	
	@GetMapping
	public ResponseEntity<List<Venda>> listar() {
		
		List<Venda> vendas = vendaService.listar();
		
		return vendas != null ? ResponseEntity.status(HttpStatus.OK).body(vendas) 
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Venda>> buscarPorId(@PathVariable Long id) {
		Optional<Venda> venda = vendaService.buscarPorId(id);
		
		return venda != null ? ResponseEntity.status(HttpStatus.OK).body(venda)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Venda venda) {
		
		venda = vendaService.salvar(venda);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> atualizar(@RequestBody Venda venda, 
			@PathVariable("id") Long id) {
		
		venda.setId(id);
		vendaService.atualizar(venda);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		vendaService.deletar(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
