package com.noorden.service.exceptions;

public class ProdutoExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProdutoExistenteException(String mensagem) {
		super(mensagem);
	}
	
	
	public ProdutoExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}