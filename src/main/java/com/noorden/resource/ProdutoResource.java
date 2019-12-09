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

import com.noorden.model.Produto;
import com.noorden.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> listar() {
		
		List<Produto> produtos = produtoService.listar();
		
		return produtos != null ? ResponseEntity.status(HttpStatus.OK).body(produtos) 
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> buscarPorId(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.buscarPorId(id);
		
		return produto != null ? ResponseEntity.status(HttpStatus.OK).body(produto)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Produto produto) {
		
		produto = produtoService.salvar(produto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> atualizar(@RequestBody Produto produto, 
			@PathVariable("id") Long id) {
		
		produto.setId(id);
		produtoService.atualizar(produto);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		produtoService.deletar(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
