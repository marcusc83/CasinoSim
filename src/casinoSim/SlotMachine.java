package casinoSim;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class SlotMachine
{
	public static Scanner in = new Scanner(System.in);
	public static Random rand = new Random();
	//the array for the spin method to pull from
	private static String [] machineImages = {"cherries" , "oranges", "bells", "plums", "melons", "bars"};
	
	//collects wager for slot games
	public static double getWager(Player Player) throws InputMismatchException
	{
		try
		{
		double wager = 0;
		boolean goodWager = false;
		do
		{
			System.out.println("Enter the amount you would like to wager");
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
			else
			{
				System.out.println("Thats not an option");
				goodWager = false;
			}
		}while(goodWager == false);
		return wager;	
		}
		catch (Exception e)
		{
			throw new InputMismatchException();
		}
	}
	
	//main logic of slot game, grabs three values from array, prints them, and checks to see how many
	//of them match, as well as sets my winnings multiplier
	public static double spin(SlotMachine SlotGame, Player Player)
	{
		double winnings = 0;
		int spinOne = rand.nextInt(6);
		int spinTwo = rand.nextInt(6);
		int spinThree = rand.nextInt(6);
		String spinOneString = "";
		String spinTwoString = "";
		String spinThreeString = "";
		
		for(int i = 0; i < machineImages.length; i ++)
		{
			if(spinOne == i)
			{
				spinOneString = machineImages[i];
			}
			if(spinTwo == i)
			{
				spinTwoString = machineImages[i];
			}
			if(spinThree == i)
			{
				spinThreeString = machineImages[i];
			}
		}
		
		System.out.println("\n* " + spinOneString + " ***** " + spinTwoString + " ***** " + spinThreeString + " *\n");
		
		if(spinOneString.equalsIgnoreCase(spinTwoString) && spinOneString.equalsIgnoreCase(spinThreeString))
		{
			System.out.println("Congrats, all three match, you tripled your wager");
			winnings = 3;
		}
		else if(spinOneString.equalsIgnoreCase(spinTwoString) || spinOneString.equalsIgnoreCase(spinThreeString))
		{
			System.out.println("Congrats, two out of the three matched, this means you doubled your wager!");
			winnings = 2;
		}
		else if(spinTwoString.equalsIgnoreCase(spinThreeString))
		{
			System.out.println("Congrats, two out of the three matched, this means you doubled your wager!");
			winnings = 2;
		}
		else
		{
			System.out.println("Sorry, none of them matched, you lose your wager");
		}
		return winnings;
	}
	
}

