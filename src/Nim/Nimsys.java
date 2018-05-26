package Nim;


import java.io.*;

/* 
* Nimsys.java
*The class to execute Nim game sys
*@author Lin Wei(885536)
*@since 05/05/2018
*/



import java.util.*;

/*
* Nimsys class is used for starting a game.
*/

public class Nimsys 
{
	private ArrayList<NimPlayer> playerArray;
	private final int MAX_RANK_NUM=10;
	private final int INITIAL_PLAYER_NUM=100;
	static Scanner keyboard;
	
	
	public Nimsys() throws IOException
	{
		playerArray= new ArrayList<NimPlayer>(INITIAL_PLAYER_NUM);
		File INFILE=new File("G:\\test.txt");
		keyboard=new Scanner(INFILE);
		//keyboard=new Scanner(System.in);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InvalidNumofArException, IOException
	{		
		Nimsys newSys=new Nimsys();
		
		System.out.println("Welcome to Nim");
		newSys.readData();
		
		while(true)
		{
			System.out.print("\n$");
			String s=keyboard.nextLine();
			try {
			newSys.splitcommand(s);
			}catch(InvalidCommandException e)
			{
				System.out.println(e.getMessage());
			}
			catch(InvalidNumofArException e)
			{
				System.out.println(e.getMessage());
			}
		}
		

	}
	private void splitcommand(String s) throws InvalidCommandException,InvalidNumofArException
	{
		String[] command=s.split(" ");           //to split command
		String[] com_obj= {""};
		
		
		if(command.length>1)                     //to execute different command
		{
			com_obj=command[1].split(",");
		}
		
		if(command[0]. equals("addaiplayer"))
		{
			if(com_obj.length<3)
			{
				throw new InvalidNumofArException();	
			}
			else 
			{
				this.addAIplayer(com_obj[0],com_obj[1],com_obj[2]);
			}
		}
		
		else if(command[0]. equals("addplayer"))
		{
			if(com_obj.length<3)
			{
				throw new InvalidNumofArException();	
			}
			else
			{
				this.addHumanplayer(com_obj[0],com_obj[1],com_obj[2]);
			}
		}	
		
		else if(command[0]. equals("removeplayer"))
		{	
			if(command.length==1)
			{
				this.removeplayer();
			}
			else
			{
				this.removeplayer(com_obj[0]);
			}
		}
		
		else if(command[0]. equals("editplayer"))
		{
			if(com_obj.length<3)
			{
				throw new InvalidNumofArException();	
			}
			else
			{
				this.editplayer(com_obj[0],com_obj[1],com_obj[2]);
			}
		}
		
		else if(command[0]. equals("resetstats"))
		{
			if(command.length==1)
			{
				this.resetstats();
			}
			else 
			{
				this.resetstats(com_obj[0]);
			}
		}
		
		else if(command[0]. equals("displayplayer"))
		{
			if(command.length==1)
			{
				this.displayplayer();
			}
			
			else
			{
				this.displayplayer(com_obj[0]);
			}
		}
		
		else if(command[0].equals("startgame"))
		{
			if(com_obj.length<3)
			{
				throw new InvalidNumofArException();	
			}
			else
			{
				try
				{	
					this.startgame(Integer.parseInt(com_obj[0]),Integer.parseInt(com_obj[1]),com_obj[2],com_obj[3]);
				}
				catch (NumberFormatException e) {
				}
			}
		}
		else if(command[0].equals("startadvancedgame"))
		{
			if(com_obj.length<2)
			{
				throw new InvalidNumofArException();	
			}
			else
			{
				try
				{	
					this.startadvancedgame(Integer.parseInt(com_obj[0]),com_obj[1],com_obj[2]);
				}
				catch (NumberFormatException e) {
				}
			}
		}
		else if(command[0].equals("rankings"))
		{
			if(command.length>1&&com_obj[0].equals("asc"))
			{
				this.ascrankings();
			}
			else
				this.dscrankings();
		}
		
		else if(command[0].equals("exit"))
		{
			this.dataSave();
			System.out.print("\n");	
			System.exit(0);
		}
		else throw new InvalidCommandException(command[0]);
	}
	
	private void addAIplayer(String username, String family_name, String given_name)
	{
		if (findPlayer(username)>=0)
				System.out.println("The player already exists.");
		else
			playerArray.add(new NimAIPlayer(username,family_name,given_name));
		
	}
	
	private void addHumanplayer(String username, String family_name, String given_name)
	{
		if (findPlayer(username)>=0)
				System.out.println("The player already exists.");
		else
			playerArray.add(new NimHumanPlayer(username,family_name,given_name));
			
	}
	
	private void removeplayer(String username)
	{
		int i=findPlayer(username);
		if(i==-1)
			System.out.println("The player does not exist.");	
		else
			playerArray.remove(i);
	}
	
	private void removeplayer()
	{
		System.out.println("Are you sure you want to remove all players? (y/n)");
		if(keyboard.nextLine().equals("y"))
		{
			playerArray.clear();
		}	
	}
	
	private void editplayer(String username,String new_family_name,String new_given_name)
	{
		int player_index;
		player_index=findPlayer(username);
		if(player_index==-1)
			System.out.printf("The player does not exist.\n");
		else
			{
				playerArray.get(player_index).setPlayerName(new_family_name,new_given_name);
			}
	}
	
	private void resetstats(String username)
	{
		int player_index;
		player_index=findPlayer(username);
		if(player_index==-1)
			System.out.printf("The player does not exist.\n");
		else
			playerArray.get(player_index).setPlayerStats(0,0);
	}
	
	private void resetstats()
	{
		System.out.println("Are you sure you want to reset all player statistics? (y/n)");
		if(keyboard.nextLine().equals("y"))
		{
			for(int i=0;i<playerArray.size();i++)
			{
				((NimPlayer) playerArray.get(i)).setPlayerStats(0,0);
			}
		}
	}
	
	private void displayplayer(String username)
	{
		int player_index=findPlayer(username);
		if(player_index==-1)
			System.out.printf("The player does not exist.\n");
		else
			System.out.println(playerArray.get(player_index).toString());
				
	}
	
	private void displayplayer()
	{
		for(int i = 0;i<playerArray.size();i++)
		{
			System.out.println(playerArray.get(sortName()[i]).toString());
		}
	}
	
	private void ascrankings()
	{
		int ascSort[]=new int[playerArray.size()];
		ascSort=sortName();
		
		for(int i=0;i<MAX_RANK_NUM&&i<playerArray.size();i++)
		{
			for(int j=playerArray.size()-1;j>i;j--)
			{
				if(winRate(ascSort[j-1])>winRate(ascSort[j]))
				{
					int tmp=ascSort[j-1];
					ascSort[j-1]=ascSort[j];
					ascSort[j]=tmp;
				}
			}
			printrankings(ascSort[i]);
		}	
	} 

	private void dscrankings()
	{
		int dscSort[]=new int[playerArray.size()];
		dscSort=sortName();
		
		for(int i=0;i<MAX_RANK_NUM&&i<playerArray.size();i++)
		{
			for(int j=playerArray.size()-1;j>i;j--)
			{
				if(winRate(dscSort[j-1])<winRate(dscSort[j]))
				{
					int tmp=dscSort[j-1];
					dscSort[j-1]=dscSort[j];
					dscSort[j]=tmp;
				}
			}
			printrankings(dscSort[i]);
		}	
	} 
	
	private int[] sortName()                          //Method to sort name index in alphabetical order
	{
		String Name[]=new String[playerArray.size()];
		int sorted_index[]=new int[playerArray.size()];
		for(int i=0;i<playerArray.size();i++)
		{
			Name[i]=playerArray.get(i).getuserName();
		}
		Arrays.sort(Name);
		for(int i=0;i<playerArray.size();i++)
		{
			sorted_index[i]=findPlayer(Name[i]);
		}
		return sorted_index;
	}
	
	private void printrankings(int rank_index)
	{
			System.out.printf("%-4s",(int)Math.round((winRate(rank_index)))+"%");
			System.out.print(" | "+String.format("%02d", playerArray.get(rank_index).getplayedCount())+" games | ");
			System.out.print(playerArray.get(rank_index).getgivenName()+" "+playerArray.get(rank_index).getfamilyName()+"\n");
	}
	
	private float winRate(int i)       //Method to calculate Win rate in PlayerArray with index i
	{
		if(playerArray.get(i).getplayedCount()==0)
			return 0;
		else
			return (((float)playerArray.get(i).getwinCount()/(float)playerArray.get(i).getplayedCount())*(float)100);
	}		
		
	private int findPlayer(String username) 
	{
		for(int i=playerArray.size()-1;i>=0;i--)
		{
			if(username.equals(((NimPlayer) playerArray.get(i)).getuserName()))
						return i;
		}
		return -1;
	}
		
	private void startgame(int initialNumber, int upperBound, String username1,String username2) 
	{
		int player_index1=findPlayer(username1);
		int player_index2=findPlayer(username2);
		if(player_index1==-1||player_index2==-1)
		{
			System.out.println("One of the players does not exist.");
		}
		else
		{
			NimGame newGame=new NimGame(initialNumber,upperBound,playerArray.get(player_index1),playerArray.get(player_index2));
			newGame.playGame();	
		}
	}
	
	public void startadvancedgame(int initialNumber,String username1,String username2)
	{
		int player_index1=findPlayer(username1);
		int player_index2=findPlayer(username2);
		if(player_index1==-1||player_index2==-1)
		{
			System.out.println("One of the players does not exist.");
		}
		else
		{
			AdvancedGame advancedGame=new AdvancedGame(initialNumber,playerArray.get(player_index1),playerArray.get(player_index2));
			advancedGame.playGame();	
		}
	}
	
	private void dataSave()
	{
		 File file = new File("players.dat");
		   try {
	            file.createNewFile();// create file
	        }
		   catch (IOException e) {
	      }
		   if (file.exists()) { 
	            this.writeData(file);    
	        } else {
	            System.out.println("file not exsists");
	        } 
	}
	
	 private void writeData(File file) {       
	        try {
	            // use ObjectOutputStream to write object into file
	        	ObjectOutputStream StasaveStream = new ObjectOutputStream(new FileOutputStream(file));
	            StasaveStream.writeObject(playerArray);
	            StasaveStream.close();//close output stream
	         
	        } catch (FileNotFoundException e) {
	            	System.out.println("write file:class not find");
	            }
	        catch (IOException e) {
	            System.out.println(e.getMessage() + "error!");
	        }
	            
	    }

	 @SuppressWarnings("unchecked")
	private void readData() throws ClassNotFoundException {
	        // read file
	        ObjectInputStream StareadStream = null;

	        try {
	        	
	        	StareadStream = new ObjectInputStream(new FileInputStream("players.dat"));//read data
	            
	            //input stream
	         
	            	playerArray=(ArrayList<NimPlayer>)StareadStream.readObject();
	    
	        
	        }
	        catch(IOException e) {
	        	dataSave();
	        }
	   }
	   
	   
}
