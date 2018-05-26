package Nim;

public class InvalidCommandException extends Exception{
	
	public InvalidCommandException() {
		super();
	}
	
	public InvalidCommandException(String message) {
		super("\'"+message+"\' is not a valid command.");
	}
}
