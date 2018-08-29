package be.vdab.personeeladministratie.exceptions;

public class PresidentNietGevondenException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public PresidentNietGevondenException(String message) {
		super(message);
	}
}