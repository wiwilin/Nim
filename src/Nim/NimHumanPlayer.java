package Nim;

import java.util.*;
import java.util.regex.Pattern;

public class NimHumanPlayer extends NimPlayer {

	/**
	 * 
	 */

	public NimHumanPlayer(String username, String family_name, String given_name) {
		super(username,family_name,given_name);// TODO Auto-generated constructor stub
	}
	/*public int removeStone(int UpperBound,int CurrentStone)throws InvalidNumException //The method to remove stone in the game
	{
		return super.removeStone(UpperBound, CurrentStone);
	}*/
	
	public NimHumanPlayer(NimHumanPlayer nimhumanplayer) {
		super(nimhumanplayer);// TODO Auto-generated constructor stub
	}

	public int removeStone(int UpperBound,int CurrentStone)throws InvalidMoveException //The method to remove stone in the game
	{
		System.out.println(givenName+"'s turn - remove how many?\n");
		String str=Nimsys.keyboard.nextLine();
		int i;
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	    if( pattern.matcher(str).matches()!=true)
	    {	
	    	throw new InvalidMoveException(String.valueOf(Math.min(UpperBound,CurrentStone)));
	    }
	    else {
	    i=Integer.parseInt(str);
		if(i<1||i>Math.min(UpperBound,CurrentStone))
		{	
			throw new InvalidMoveException(String.valueOf(Math.min(UpperBound,CurrentStone)));
		}
		
	    }
		return i;
	}
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = Nimsys.keyboard.nextLine();
		return move;
	}
}
