package com.noorden.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.noorden.model.Produto;
import com.noorden.repository.ProdutoRepository;
import com.noorden.service.exceptions.ProdutoExistenteException;
import com.noorden.service.exceptions.ProdutoNaoEncontradoException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	public Produto salvar(Produto produto) {
		
		if(produto.getId() != null) {
			Optional<Produto> p = produtoRepository.findById(produto.getId());
		
			if(p != null) {
				throw new ProdutoExistenteException("O produto já existe!");
			}
		
		}
		return produtoRepository.save(produto);
	}
	
	public Optional<Produto> buscarPorId(Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto == null) {
			
			throw new ProdutoNaoEncontradoException("O Id do produto não pode ser encontrato!");
		}
		
		return produto;
	}
	
	public void deletar(Long id) {
		
		try {
			produtoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException("O produto não pôde ser encontrado!");
		}
	}
	
	public void atualizar(Produto produto) {
		
		verificarExistencia(produto);
		produtoRepository.save(produto);
	}
	
	public void verificarExistencia(Produto produto) {
		buscarPorId(produto.getId());
	}
	
}
