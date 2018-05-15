package domain;

public class DomainException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DomainException() {
		
	}
	
	public DomainException(String message) {
		super(message);
	}
	
	public DomainException(Exception e) {
		super(e);
	}
	
	public DomainException(String message, Exception e) {
		super(message);
	}
}