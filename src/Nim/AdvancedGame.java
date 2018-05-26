package Nim;

public class AdvancedGame {
	private int currentStone;
	private boolean[] existStone;
	private NimPlayer NimPlayer1;
	private NimPlayer NimPlayer2;
	
	public AdvancedGame(int initialStone, NimPlayer NimPlayer1, NimPlayer NimPlayer2)
	{
		this.currentStone=initialStone;
		this.existStone=new boolean[initialStone+1];
		for(int i=0;i<=initialStone;i++)
		{
			existStone[i]=true;
		}
		this.NimPlayer1=(NimPlayer) NimPlayer1;
		this.NimPlayer2=(NimPlayer) NimPlayer2;
	}
	
	public void playGame()
	{
		int removeNumber=0;//The number of stone removed each time 
		
		final int NONE=0;//None stone left
		final int PLAYER1TURN=1;//Now the player is NimPlayer1
		final int PLAYER2TURN=2;//Now the player is NimPlayer2
		
		System.out.println("\nInitial stone count: "+currentStone);
		displayStone();
		System.out.println("Player 1: "+NimPlayer1.getgivenName()+" "+NimPlayer1.getfamilyName());
		System.out.println("Player 2: "+NimPlayer2.getgivenName()+" "+NimPlayer2.getfamilyName()+"\n");
		
		int nowplayerID=PLAYER1TURN;//When the game starts, is NimPlayer1's turn
		String lastRemove=" ";
		
		while(currentStone>NONE)              //If there is stone left, print number
		{
			System.out.print(currentStone+" stones left:");
				
			displayStone();
			
		    
			switch(nowplayerID)            //Players take turns to play
			{
				case PLAYER1TURN:
				System.out.println(NimPlayer1.getuserName()+"'s turn - which to remove?");
				try {
					lastRemove=advancedRomove(NimPlayer1.advancedMove(existStone,lastRemove));
					nowplayerID=PLAYER2TURN;
				} catch (InvalidMoveException e) {
					System.out.println(e.getMessage()+"\n");
				}
				break;
				
				case PLAYER2TURN:
				System.out.println(NimPlayer2.getuserName()+"'s turn - which to remove?");
				try {
					lastRemove=advancedRomove(NimPlayer2.advancedMove(existStone,lastRemove));
					nowplayerID=PLAYER1TURN;
				} catch (InvalidMoveException e) {
					System.out.println(e.getMessage()+"\n");
				}
				break;
			}
		
		}	
			
		System.out.print("Game Over\n");
		NimPlayer1.setplayedCount(NimPlayer1.getplayedCount()+1);
		NimPlayer2.setplayedCount(NimPlayer2.getplayedCount()+1);
	    
	    if(nowplayerID==2)
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
	
	private String advancedRomove(String lastRemove) throws InvalidMoveException {
		String[]numstr=lastRemove.split(" ");
		int startposition=Integer.parseInt(numstr[0]);
		int shift=Integer.parseInt(numstr[1]);
		if(startposition<1||startposition>existStone.length-1||shift<1|shift>2)
			throw new InvalidMoveException();
		for(int i=0;i<shift;i++)
		{
			if(existStone[startposition+i]=false)
				throw new InvalidMoveException();
			existStone[startposition+i]=false;
		}
		currentStone-=shift;
		return lastRemove;
	}

	private void displayStone() //The method to print the stones
	{
		System.out.print("Stones display:");
		for(int i=1;i<=existStone.length-1;i++)
		{
			System.out.print("<"+i+",");
			if(existStone[i]==true)
				System.out.print("*");
			else
				System.out.print("x");
			System.out.print("> ");
		}
		System.out.print("\n");
	}	
}
