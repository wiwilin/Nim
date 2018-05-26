package Nim;

public class InvalidMoveException extends Exception{

	public InvalidMoveException(){
		super("Invalid move");
	}
	
	public InvalidMoveException(String message){
		super("Invalid move. You must remove between 1 and "+message+" stones.\n");
	}
}

