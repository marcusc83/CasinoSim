package casinoSim;
import java.util.Random;
import java.util.Scanner;

//BlackJack class keeps count of both dealer's and player's score
	//also has typical getter and setter for fields and a to string method
	public class BlackJack
	{
		public Scanner in = new Scanner(System.in);
		public Random rand = new Random();
		int dealerScore = 0;
		int playerScore = 0;
		
		public BlackJack()
		{
			dealerScore = 0;
			playerScore = 0;
		}
		
		public void setDealerScore(int dealerScore)
		{
			this.dealerScore = dealerScore;
		}
		
		public void setPlayerScore(int playerScore)
		{
			this.playerScore = playerScore;
		}
		
		public int getDealerScore()
		{
			return dealerScore;
		}
		
		public int getPlayerScore()
		{
			return playerScore;
		}
		
		public String toString(BlackJack Game, Player Player)
		{
			String str = Player.getName() + " Score: " + Game.getPlayerScore() + "\n" + 
					"Dealer score: " + Game.getDealerScore();
			return str;
		}
		
		//this method checks to see if the player hit 21
		public boolean checkfor21(BlackJack Game)

		{
			boolean twentyOne = false;
			if (Game.playerScore == 21)
			{
				twentyOne = true;
				System.out.println("You hit 21, that means you tripled your wager!!!");
			}
			return twentyOne;
		}
		
		//welcomes player to game
		public void blackjackIntro(Player Player)
		{
			System.out.println("Welcome to the blackJack table, let's get started!\n");
		}
		
		//collects and returns player wager
		public double getWager(Player Player)
		{
			boolean goodWager = false;
			double wager = 0;
			
			while(goodWager == false)
			{
				System.out.println("Enter the amount you would like to wager\n");
				wager = in.nextDouble();
			
			
				if(wager > Player.getAccount())
				{
					System.out.println("You don't have that much money, try again");
					goodWager = false;
				}
				else if(wager == 0)
				{
					System.out.println("You have to bet more than zero");
					goodWager = false;
				}
				else if(wager > 0 && wager <= Player.getAccount())
				{
					System.out.println("Thats a good wager, lets do this");
					goodWager = true;
				}
			}
			return wager;	
		}
		
		//finds out who actually won and returns true if player wins
		public boolean getWinner(BlackJack Game)
		{
			boolean playerWon = false;
			if(Game.playerScore > Game.dealerScore)
			{
				if(Game.playerScore <= 21)
				{
					if(Game.dealerScore > 21)
						
						{
							System.out.println("Congrats, you won with a score of " + Game.playerScore + 
									" versus the dealer's score of " + Game.dealerScore + "!");
							playerWon = true;
						}
				}
			}
			else if(Game.dealerScore > 21)
			{
				if(Game.playerScore <= 21)
				{
					System.out.println("Congrats, the dealer busted!");
					playerWon = true;
				}
			}
			else
			{
				System.out.println("Sorry, you lost with a score of " + Game.playerScore + 
						" versus the dealer's score of " + Game.dealerScore);
				playerWon = false;
			}
			return playerWon;
		}
	}
