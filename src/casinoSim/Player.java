package casinoSim;

import java.util.Random;
import java.util.Scanner;
//my player class, keeps track of name and accounts
//pretty basic fields with getters and setters and to string method
	
public class Player
{
	private String name;
	private double account;
	 public static Scanner in = new Scanner(System.in);
	 public static Random rand = new Random();
	
	public Player(String name, double account)
	{
		this.name = name;
		this.account = account;
	}
	
	public Player()
	{
		name = "";
		account = 00.00;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setAccount(double account)
	{
		this.account = account;
	}
	
	public double getAccount()
	{
		return account;
	}
	
	public String toString(Player player)
	{
		String str = name + "'s account: $" + account;
		return str;
	}
	
	//makes sure player has money before they are allowed to play
	public boolean checkAccount(Player Player)
	{
		boolean broke = false;
		if(Player.getAccount() <= 0)
		{
			System.out.println("Sorry, " + Player.getName() + " your account is out of money, deposit more or go home.");
			broke = true;
		}
		else
		{
			System.out.println(Player.getName() + " you have $" + Player.getAccount() + " in your account, good luck!");
			broke = false;
		}
		return broke;
	}
	
	//gives option to add money if player runs out
	public void addMoney(Player Player)
	{
		
		System.out.println("Would you like to add money? (y/n)");
		String choice = in.nextLine();
		if(choice.equalsIgnoreCase("y")) 
		{
			System.out.println("How much money would you like to add?");
			double moneyAdded = in.nextDouble();
			Player.setAccount(moneyAdded);
		}
		else if(choice.equalsIgnoreCase("n"))
		{
			System.out.println("Goodebye");
			System.exit(0);
		}
		
	}
	
	// getting initial deposit for player
	public static double getDeposit()
	{
		boolean goodDeposit = false;
		double deposit = 0;
		while(goodDeposit == false)
		{	
			System.out.println("Enter your deposit ammount\n"
					+ "Min deposit = $20.00\n"
					+ "Max deposit = $1000.00");
			deposit = in.nextDouble();
			
			if((deposit <= 1000) && (deposit >= 20))
			{
				System.out.println("Thats a good deposit");
				goodDeposit = true;
			}
			else
			{
				System.out.println("That is not within our parameters");
				goodDeposit = false;
			}
		}
	return deposit;
	}
}
