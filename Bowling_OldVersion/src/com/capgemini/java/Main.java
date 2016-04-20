package com.capgemini.java;

public class Main {

	public static void main(String[] args) {
		// Testing example from lecture (ppt)
		Bowling myBowl = new Bowling();

		myBowl.roll(10);
		System.out.println("Number of pins: " + 10 + ". Score: " + myBowl.score());

		myBowl.roll(9);
		System.out.println("Number of pins: " + 9 + ". Score: " + myBowl.score());

		myBowl.roll(1);
		System.out.println("Number of pins: " + 1 + ". Score: " + myBowl.score());

		myBowl.roll(5);
		System.out.println("Number of pins: " + 5 + ". Score: " + myBowl.score());

		myBowl.roll(5);
		System.out.println("Number of pins: " + 5 + ". Score: " + myBowl.score());

		myBowl.roll(7);
		System.out.println("Number of pins: " + 7 + ". Score: " + myBowl.score());

		myBowl.roll(2);
		System.out.println("Number of pins: " + 2 + ". Score: " + myBowl.score());

		myBowl.roll(10);
		System.out.println("Number of pins: " + 10 + ". Score: " + myBowl.score());

		myBowl.roll(10);
		System.out.println("Number of pins: " + 10 + ". Score: " + myBowl.score());

		myBowl.roll(10);
		System.out.println("Number of pins: " + 10 + ". Score: " + myBowl.score());

		myBowl.roll(9);
		System.out.println("Number of pins: " + 9 + ". Score: " + myBowl.score());

		myBowl.roll(0);
		System.out.println("Number of pins: " + 0 + ". Score: " + myBowl.score());

		myBowl.roll(8);
		System.out.println("Number of pins: " + 8 + ". Score: " + myBowl.score());

		myBowl.roll(2);
		System.out.println("Number of pins: " + 2 + ". Score: " + myBowl.score());

		myBowl.roll(9);
		System.out.println("Number of pins: " + 9 + ". Score: " + myBowl.score());

		myBowl.roll(1);
		System.out.println("Number of pins: " + 1 + ". Score: " + myBowl.score());

		myBowl.roll(10);
		System.out.println("Number of pins: " + 10 + ". Score: " + myBowl.score());
	}

}
