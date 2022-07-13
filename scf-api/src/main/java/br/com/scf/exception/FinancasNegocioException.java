package br.com.scf.exception;

public class FinancasNegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FinancasNegocioException(String msg) {
		super(msg);
	}
}