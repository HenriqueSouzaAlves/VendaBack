package com.noorden.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.noorden.model.Venda;
import com.noorden.repository.VendaRepository;
import com.noorden.service.exceptions.VendaExistenteException;
import com.noorden.service.exceptions.VendaNaoEncontradaException;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	public List<Venda> listar() {
		return vendaRepository.findAll();
	}
	
public Venda salvar(Venda venda) {
		
		if(venda.getId() != null) {
			Optional<Venda> v = vendaRepository.findById(venda.getId());
		
			if(v != null) {
				throw new VendaExistenteException("A venda já existe!");
			}
		
		}
		return vendaRepository.save(venda);
	}
	
	public Optional<Venda> buscarPorId(Long id) {
		
		Optional<Venda> venda = vendaRepository.findById(id);
		
		if(venda == null) {
			
			throw new VendaNaoEncontradaException("O Id da venda não pode ser encontrato!");
		}
		
		return venda;
	}
	
	public void deletar(Long id) {
		
		try {
			vendaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new VendaNaoEncontradaException("A venda não pôde ser encontrado!");
		}
	}
	
	public void atualizar(Venda venda) {
		
		verificarExistencia(venda);
		vendaRepository.save(venda);
	}
	
	public void verificarExistencia(Venda venda) {
		buscarPorId(venda.getId());
	}
	
}
