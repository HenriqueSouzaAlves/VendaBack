package com.noorden.service.exceptions;

public class VendaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VendaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	
	public VendaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}