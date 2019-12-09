package com.noorden.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.noorden.model.Cliente;
import com.noorden.repository.ClienteRepository;
import com.noorden.service.exceptions.ClienteExistenteException;
import com.noorden.service.exceptions.ClienteNaoEncontradoException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	public Cliente salvar(Cliente cliente) {
		
		if(cliente.getId() != null) {
			Optional<Cliente> c = clienteRepository.findById(cliente.getId());
		
			if(c != null) {
				throw new ClienteExistenteException("O cliente já existe!");
			}
		
		}
		return clienteRepository.save(cliente);
	}
	
	public Optional<Cliente> buscarPorId(Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(cliente == null) {
			
			throw new ClienteNaoEncontradoException("O Id cliente não pode ser encontrato!");
		}
		
		return cliente;
	}
	
	public void deletar(Long id) {
		
		try {
		clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradoException("O cliente não pôde ser encontrado!");
		}
	}
	
	public void atualizar(Cliente cliente) {
		
		verificarExistencia(cliente);
		clienteRepository.save(cliente);
	}
	
	public void verificarExistencia(Cliente cliente) {
		buscarPorId(cliente.getId());
	}
	
}
