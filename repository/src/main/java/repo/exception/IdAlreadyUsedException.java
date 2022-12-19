package repo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class IdAlreadyUsedException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private int ID;

	
	public IdAlreadyUsedException(String resourceName, int ID) {
		super(String.format("%s ID already used with %s ", resourceName, ID));
		this.resourceName = resourceName;
		this.ID = ID;
		
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	public int getID() {
		return ID;
	}
	
	
	
}
