package Nim;

public class InvalidNumofArException extends Exception{
	
	public InvalidNumofArException() {
		super("Incorrect number of arguments supplied to command.");
	}
	
	public InvalidNumofArException(String message) {
		super(message);
	}
}
