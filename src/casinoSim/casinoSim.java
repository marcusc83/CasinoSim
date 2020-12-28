package casinoSim;

import java.util.Scanner ;

import java.util.Random ;


public class casinoSim 
{
	 public static Scanner in = new Scanner(System.in);
	 public static Random rand = new Random();
		
	public static void main(String[] args) 
	{
		//getting info to fill Player class
		System.out.println("Welcome to the casino\nI am going to get some info from you");
		System.out.println("Please enter your name");
		String name = in.nextLine();
		double deposit = Player.getDeposit();
		Player Player = new Player(name, deposit);
		// pLayer filled
		
		//making sure they have money to play
		if(Player.checkAccount(Player) == true)
		{	
			Player.addMoney(Player);
		}
		
		//setting value to test main loop against
		int choice = -1;
		
		//this loop covers both games and the menu
		do
		{
			//allows user to choice game
			choice = gameChoice();
			
			//using switch structure to decide where the program goes
			switch(choice)
			{
				case 1:
					
					boolean playAgain = false;
					System.out.println("You chose blackjack, good luck");
					
					//this loop controls blackjack game
					//tests against playAgain bool
					do {
						
						//setting up game and getting wagers
						BlackJack Game = new BlackJack();
						
						Game.blackjackIntro(Player);
						
						if(Player.checkAccount(Player) == true)
						{	
							Player.addMoney(Player);
						}
						
						double wager = Game.getWager(Player);
						
						Player.setAccount((Player.getAccount() - wager)); 
						// done setting up game 
						
						//this bool will control some loops down the road
						boolean hitMe = true;
						
						// getting card from deck to create score
						Deck Deck = new Deck();
						
						//this is the beginning of main game logic
						//handing out initial cards
						Game.playerScore += Deck.getCard(Deck);
						Game.dealerScore += Deck.getCard(Deck);
						Game.playerScore += Deck.getCard(Deck);
						
						//checking to see if player hit 21
						boolean twentyone = Game.checkfor21(Game);
						if(twentyone == true)
						{
							Player.setAccount((Player.getAccount() + (wager * 3))); 
							System.out.println("Congrats you tripled your money");
							hitMe = false;
						}
						
						// using this loop to let player keep getting cards
						// until they choose not to or they go over 21
						while (hitMe == true)
						{
							
							hitMe = Deck.hitMe(Game, Player);
							
							if(hitMe == true && Game.playerScore <= 21)
							{
								Game.playerScore += Deck.getCard(Deck);
								System.out.println(Game.toString(Game, Player));
								twentyone = Game.checkfor21(Game);
								if(twentyone == true)
								{
									Player.setAccount((Player.getAccount() + (wager *3)));
									hitMe = false;
								}
								if(Game.playerScore > 21)
								{
									System.out.println("Sorry you went over twenty-one, maybe next hand");
									hitMe = false;
								}
							}
						}
						
						if(hitMe == false && Game.getPlayerScore() < 21 && Game.getPlayerScore() != 21)
						{
							System.out.println("Player's total score: " + Game.playerScore + "\nNow the dealers turn");
						}
						
						while(Game.dealerScore <= 17)
						{
							Game.dealerScore += Deck.getCard(Deck);
							System.out.println("Dealer Score: " + Game.dealerScore);
						}
						
						boolean playerwon = Game.getWinner(Game);
						
						//deciding if player won and setting their account accordingly
						if(playerwon == true)
						{
							Player.setAccount((Player.getAccount() + (wager * 2)));
						}
						
						// checking if player would like to play another hand
						playAgain = playAgain();
						if(playAgain == false)
						{
							//this gives user choice to go back to menu or quit altogether
							choice = toMenu_or_toQuit();
						}
						
					}while(playAgain == true);
					
					break;
					
				case 2:
					System.out.println("You chose to play the slots, good luck");
					playAgain = false;
					
					do 
					{
						//creating a new slot machine instance
						SlotMachine SlotGame = new SlotMachine();
						
						//checking player account
						if(Player.checkAccount(Player) == true)
						{	
							Player.addMoney(Player);
						}
						
						// getting wager and subtracting it from player account
						double wager = SlotMachine.getWager(Player);
						Player.setAccount((Player.getAccount() - wager));
						
						//running the game and printing results.
						double winningsMultiplier = SlotMachine.spin(SlotGame, Player);
						Player.setAccount(Player.getAccount() + (wager * winningsMultiplier));
						
						playAgain = playAgain();
						if(playAgain == false)
						{
							choice = toMenu_or_toQuit();
						}
						
						
					} while(playAgain == true);
					
					break;
					
				case 3:
					System.out.println("You have $" + Player.getAccount());
					choice = -1;
					break;
					
				case 4:
					System.out.println("Good-Bye!");
					choice = 99;
					break;
					
				default:
					System.out.println("Not an option, try again.");
					choice = -1;
					break;
			}
		}while(choice == -1);
		
		//closing Scanner and exiting program
		in.close();
		System.exit(0);
	}
	
	//gets integer used in switching statement
	public static int gameChoice()
	{
		int choice = 0;
		
			System.out.println("Enter 1 to play blackjack\n"
					+ "Enter 2 to play the slots\n"
					+ "Enter 3 to check your account\n"
					+ "Enter 4 to quit");
			choice = in.nextInt();
			
		return choice;
	}
	
	//gets boolean to test against 
	public static boolean playAgain()
	{
		int choice;
		boolean playAgain = false;
		do
		{
			System.out.println("Would you like to play again?(y/N)");
			String playAgainString = in.nextLine();
			
			if(playAgainString.equalsIgnoreCase("y"))
			{
				playAgain = true;
				choice = 0;
			}
			else if(playAgainString.equalsIgnoreCase("n"))
			{
				playAgain = false;
				choice = 0;
			}
			else
			{
				choice = 1 ;
			}
			
		}while(choice ==1);
		return playAgain;
	}
	
	//logic to go to menu or quit
	public static int toMenu_or_toQuit()
	{
		String str = "";
		int choice = 99;
		do
		{
			System.out.println("Would you like to go to menu or just quit?('menu/'quit')");
			str = in.nextLine();
			if(str.equalsIgnoreCase("menu"))
			{
				choice = -1;
			}
			else if(str.equalsIgnoreCase("quit"))
			{
				System.exit(0);
			}
			else
			{
				System.out.println("Not an valid option, enter 'quit' or 'menu'");
				choice = 99;
			}
			
		}while(choice == 99);
		return choice ;
	}
}