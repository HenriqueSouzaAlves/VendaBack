package com.noorden.service.exceptions;

public class VendaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VendaExistenteException(String mensagem) {
		super(mensagem);
	}
	
	
	public VendaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}