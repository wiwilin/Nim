package Nim;

import java.io.*;

/* 
* NimPlayer.java
*The class to set players
*@author Lin Wei(885536)
*@since 05/05/2018
*/

/*
 * NimPlayer class is used for game players.
 */

public abstract class NimPlayer implements Serializable
{
	/**
	 * 
	 */
	protected String userName;
	protected String givenName;
	protected String familyName;
	protected int playedCount;
	protected int winCount;
	
	public NimPlayer()
	{
	    userName=null;
		givenName=null;
		familyName=null;
		playedCount=0;
		winCount=0;
	}

	public NimPlayer(NimPlayer Nimplayer)
	{
	    userName=Nimplayer.userName;
		givenName=Nimplayer.givenName;
		familyName=Nimplayer.familyName;
		playedCount=Nimplayer.playedCount;
		winCount=Nimplayer.winCount;
	}
	
	public NimPlayer(String userName,String familyName,String givenName)
	{
		this.userName=userName;
		this.familyName=familyName;
		this.givenName=givenName;
		this.playedCount=0;
		this.winCount=0;
	}
	
	public void setPlayerName(String familyName,String givenName)
	{
		this.familyName=familyName;
		this.givenName=givenName;
	}
	
	public String getuserName()
	{
		return this.userName;
	}
	
	public String getgivenName()
	{
		return this.givenName;
	}
	
	public String getfamilyName()
	{
		return this.familyName;
	}
	
	public void setplayedCount(int count)
	{
		this.playedCount=count;
	}
	
	public int getplayedCount()
	{
		return this.playedCount;
	}
	
	public void setwinCount(int count)
	{
		this.winCount=count;
	}
	
	public int getwinCount()
	{
		return this.winCount;
	}
	
	public void setPlayerStats(int playedCount,int winCount)
	{
		this.playedCount=playedCount;
		this.winCount=winCount;
	}
	
	public abstract int removeStone(int UpperBound,int CurrentStone)throws InvalidMoveException;
	
	
	public String toString() 
	{
		return  userName + "," + givenName + "," + familyName
				+ "," + playedCount + " games," + winCount + " wins";
	}
	public abstract String advancedMove(boolean[] available, String lastMove);
	
}

