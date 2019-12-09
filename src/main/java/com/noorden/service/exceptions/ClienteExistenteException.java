package com.noorden.service.exceptions;

public class ClienteExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteExistenteException(String mensagem) {
		super(mensagem);
	}
	
	
	public ClienteExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
