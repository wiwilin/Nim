package Nim;

/* 
* NimGame.java
*The class to play a round of game
*@author Lin Wei(885536)
*@since 05/05/2018
*/

/*
 * NimPlayer class is used for a round of Nim game.
 */

public class NimGame
{
	private int currentStone;
	private int upperBound;
	private NimPlayer NimPlayer1;
	private NimPlayer NimPlayer2;
	
	public NimGame(int currentStone, int upperBound, NimPlayer NimPlayer1, NimPlayer NimPlayer2)
	{
		this.currentStone=currentStone;
		this.upperBound=upperBound; 
		this.NimPlayer1=NimPlayer1;
		this.NimPlayer2=NimPlayer2;
	}
	
	public void playGame()
	{
		int removeNumber=0;//The number of stone removed each time 
		
		final int NONE=0;//None stone left
		final int PLAYER1TURN=1;//Now the player is NimPlayer1
		final int PLAYER2TURN=2;//Now the player is NimPlayer2
		
		System.out.println("\nInitial stone count: "+currentStone);
		System.out.println("Maximum stone removal: "+upperBound);
		System.out.println("Player 1: "+NimPlayer1.getgivenName()+" "+NimPlayer1.getfamilyName());
		System.out.println("Player 2: "+NimPlayer2.getgivenName()+" "+NimPlayer2.getfamilyName()+"\n");
		
		int nowplayerID=PLAYER1TURN;//When the game starts, is NimPlayer1's turn
		
		while(currentStone>NONE)              //If there is stone left, print number
		{
			System.out.print(currentStone+" stones left:");
				
			printStone(currentStone);
			
		    
			switch(nowplayerID)            //Players take turns to play
			{
				case PLAYER1TURN:
				try{
					removeNumber=NimPlayer1.removeStone(upperBound,currentStone);
				}catch(InvalidMoveException e)
				{
					System.out.println(e.getMessage());
					removeNumber=0;
				}
				break;
				
				case PLAYER2TURN:
				try{
					removeNumber=NimPlayer2.removeStone(upperBound,currentStone);
				}catch(InvalidMoveException e)
				{
					System.out.println(e.getMessage());
					removeNumber=0;
				}
				break;
			}
			if(removeNumber!=0)
			{
				currentStone-=removeNumber;
				nowplayerID=((nowplayerID==PLAYER1TURN)?PLAYER2TURN:PLAYER1TURN);//Change the player
			}
		}	
			
		System.out.print("Game Over\n");
		NimPlayer1.setplayedCount(NimPlayer1.getplayedCount()+1);
		NimPlayer2.setplayedCount(NimPlayer2.getplayedCount()+1);
	    
	    if(nowplayerID==1)
	    {
	    	NimPlayer1.setwinCount(NimPlayer1.getwinCount()+1);
	    	System.out.println(NimPlayer1.getgivenName()+" "+NimPlayer1.getfamilyName()+" wins!");//Print winner's name		
	    }
	    	
	    else
	    {
	    	NimPlayer2.setwinCount(NimPlayer2.getwinCount()+1);
	    	System.out.println(NimPlayer2.getgivenName()+" "+NimPlayer2.getfamilyName()+" wins!");
	    }
	}
	
	private void printStone(int numofStone) //The method to print the stones
	{
		for(int i=numofStone;i>0;i--)
		{
			System.out.print(" *");
		}
		System.out.print("\n");
	}	
	
}
