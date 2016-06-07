package com.capgemini.main;

import java.util.Random;

public class RandomNumberGenerator {

	private static Random rng = new Random();

	public static int randomize(int lowerBound, int upperBound) {
		return rng.nextInt(upperBound - lowerBound) + lowerBound;
	}

}
