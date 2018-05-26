package Nim;

import java.util.Random;

/*
NimAIPlayer.java

This class is provided as a skeleton code for the tasks of 
Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
to finish the tasks. 
*/

public class NimAIPlayer extends NimPlayer implements Testable{
// you may further extend a class or implement an interface
// to accomplish the tasks.	

//public NimAIPlayer() {
//	super(null);
//}
	
/**
	 * 
	 */

public NimAIPlayer(String username, String family_name, String given_name) {
	super(username,family_name,given_name);// TODO Auto-generated constructor stub
}

public NimAIPlayer(NimAIPlayer nimaiplayer) {
	super(nimaiplayer);
	// TODO Auto-generated constructor stub
}

public int removeStone(int UpperBound,int currentStone) //The method to remove stone in the game
{
	Random random=new Random();
	System.out.println(givenName+"'s turn - remove how many?\n");
	int i=currentStone-1-(currentStone-1)/(UpperBound+1)*(UpperBound+1);
	if(i<1||i>UpperBound)
		i=random.nextInt(UpperBound-1)+1;
	return i;
}

public String advancedMove(boolean[] available, String lastMove) {
	// the implementation of the victory
	// guaranteed strategy designed by you
	int rpos=1;
	int rmov=0;
	int len=available.length-1;
	boolean[] ava=new boolean[len+1];

	if (lastMove==" ")
	{
		if( len%2==1)
		{
			rpos=(len+1)/2;
			rmov=1;
		}
		else
		{
			rpos=len/2;
			rmov=2;
		}
	}
	else 
	{
	int count=0;
	int dcount=0;
	String[]str=lastMove.split(" ");
	int lpos=Integer.parseInt(str[0]);
	int lmov=Integer.parseInt(str[1]);
	ava[0]=true;
	for(int i=1;i<=len;i++)
	{
		ava[i]=available[i];
		if(available[i]==true)
		{	
			count++;
			rpos=i;
			rmov=1;
			if(i<len&&available[i+1]==true)
			{
				
				rmov=2;
				i++;
				dcount++;
			}
		}
   }	

	

			if(count%2==1)
			{
				rpos=len-lpos-lmov+2;
				rmov=lmov;
				if(available[rpos]==false&&available[rpos+rmov-1]==true)
				{
					rpos=rpos+1;
					rmov=1;
				}
				else if(available[rpos]==true&&available[rpos+rmov-1]==false)
				{
					rmov=1;
				}
				else if(available[rpos]==false&&available[rpos+rmov-1]==false)
				{
					if(dcount%2==0)
					{
						for(int i=1;i<=len;i++)
						{
							if(ava[i]=true&&ava[len-i+1]==false)
							{
								ava[i]=false;
								if(checksyn(ava)==true)
									rpos=i;
								else
									ava[i]=true;
							}
						}
					}
					else
					{
						for(int i=1;i<=len;i++)
						{
							if(ava[i]=true&&ava[len-i+1]==true)
							{
								ava[i]=false;
								ava[i+1]=false;
								if(checksyn(ava)==true)
									rpos=i;
								else
									{
									ava[i]=true;
									ava[i+1]=true;
									}
									}
							}
						}	
					}
					
				}
			

}
	
	String move=String.valueOf(rpos)+" "+String.valueOf(rmov);
	return move;
}

public boolean checksyn(boolean[] available)
{
	int count=0;
	
	for(int i=1;i<=available.length;i++)
	{
		if(available[i]==true)
		{	
			count++;
			
			if(available[i+1]=true)
			{
				
				i++;
			}
		}
   }
	if (count%2==0)
		return true;
	else
		return false;	
}
}