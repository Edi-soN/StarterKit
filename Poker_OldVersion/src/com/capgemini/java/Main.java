package com.capgemini.java;

public class Main {

	public static void main(String[] args) {
		Poker pokerGame = new Poker();
		
		pokerGame.firstPlayer.pickCard("9", "S");
		pokerGame.firstPlayer.pickCard("9", "D");
		pokerGame.firstPlayer.pickCard("9", "C");
		pokerGame.firstPlayer.pickCard("9", "H");
		pokerGame.firstPlayer.pickCard("10", "S");
		System.out.print("First player cards: " +pokerGame.firstPlayer.toString());
		
		pokerGame.secondPlayer.pickCard("A", "S"); 
		pokerGame.secondPlayer.pickCard("A", "D"); 
		pokerGame.secondPlayer.pickCard("A", "H"); 
		pokerGame.secondPlayer.pickCard("A", "C");
		pokerGame.secondPlayer.pickCard("J", "S"); 
		System.out.print("Second player cards: " +pokerGame.secondPlayer.toString());
		
		if(pokerGame.getWinner()==1)
			System.out.println("First player wins!");
		else if(pokerGame.getWinner()==2)
			System.out.println("Second player wins!");
		else
			System.out.println("We got a tie!");
		
	}

}
