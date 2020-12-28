package casinoSim;
import java.util.Random;
import java.util.Scanner;

//this is where I went AWOl from the instructions and created a deck
	//instead of dice, hope thats okay
	public class Deck
	{
		public static Scanner in = new Scanner(System.in);
		public static Random rand = new Random();
		//creating my deck, i tried to do the same odds as a typical 52 card deck
		//minus the ace being either one or eleven
		int [] deck = {2,3,4,5,6,7,8,9,10,10,10,10,11};
		
		//generating a random number and assigning it to a value in my deck array
		public int getCard(Deck Deck)
		{
			int i = rand.nextInt(12) + 1;	
			return deck[i];
		}
		
		//this method is going to decide if a player wants another card
		public boolean hitMe(BlackJack Game, Player Player)
		{
			boolean hitMe = false;
			boolean looper = false;
			
			while(looper == false)
			{
				System.out.println(Game.toString(Game, Player) + "\nWould you like another card(y/n)?");
				String anotherCard = in.nextLine();
			
				if (anotherCard.equalsIgnoreCase("y"))
				{
					hitMe = true;
					looper = true;
				}
				else if(anotherCard.equalsIgnoreCase("n"))
				{
					hitMe = false;
					looper = true;
				}
				else
				{
					System.out.println("Not a valid response. try again.");
					looper = false;
				}
			}
			return hitMe;
		}
		
	}
