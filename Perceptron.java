package main;

import java.io.*;
import java.util.*;

public class Perceptron implements Comparable<Object> {
	private Letter[] letters;
	private boolean[] correctLetter;
	private static Perceptron instance = null;

	private Perceptron() {
		correctLetter = new boolean[27];
		this.letters = new Letter[27];
		initialize();
	}

	private void initialize() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
		String bitCode = "00000000000000000000000000000000000";
		for (int index = 0; index < letters.length; index++) {
			letters[index] = new Letter(bitCode + characters.charAt(index));
		}
	}

	public void trainTemplates(File traingFile) throws FileNotFoundException {
		while (allTrue()) {
			Scanner inScan = new Scanner(traingFile);
			while (inScan.hasNext()) {
				String bitCode = inScan.nextLine();
				Letter pattern = new Letter(bitCode);
				int guess = guess(pattern);
				int correct = findCorrect(pattern);
				checkLearn(guess, correct, pattern);
			}
			inScan.close();
			printArray();
			System.out.println();
			System.out.println();
		}
	}

	private void printArray() {
		for (int index = 0; index < correctLetter.length; index++) {
			System.out.println(correctLetter[index]);
		}
	}

	private boolean allTrue() {
		for (int index = 0; index < correctLetter.length; index++) {
			if (!correctLetter[index]) {
				return true;
			}
		}
		return false;
	}

	public void recognizeCharacters(File recognitionFile, File outputFile)
			throws FileNotFoundException {
		Scanner inScan = new Scanner(recognitionFile);
		while (inScan.hasNext()) {
			String bitCode = inScan.nextLine();
			Letter pattern = new Letter(bitCode);
			int index = guess(pattern);
		}
		inScan.close();
	}

	public static Perceptron getInstance() {
		if (instance == null) {
			instance = new Perceptron();
		}
		return instance;
	}

	private int correlate(Letter template, Letter pattern) {
		int sum = 0;
		int[] tLetter = template.getBits();
		int[] pLetter = pattern.getBits();
		for (int index = 0; index < tLetter.length; index++) {
			sum += tLetter[index] * pLetter[index];
		}
		return sum;
	}

	private int guess(Letter pattern) {
		int max = 0;
		int index = -1;
		for (int count = 0; count < letters.length; count++) {
			int sum = correlate(letters[count], pattern);
			if (sum > max) {
				max = sum;
				index = count;
			}
		}
		return index;
	}

	private int findCorrect(Letter pattern) {
		for (int index = 0; index < letters.length; index++) {
			if (letters[index].getLetter() == pattern.getLetter()) {
				return index;
			}
		}
		return 0;
	}

	private void checkLearn(int guess, int correct, Letter pattern) {
		if (guess != correct) {
			reward(correct, pattern);
			punish(correct, pattern);
		}

		else {
			correctLetter[correct] = true;
		}
	}

	public void reward(int correctIndex, Letter pattern) {
		int[] pBits = pattern.getBits();
		int[] lBits = letters[correctIndex].getBits();
		for (int index = 0; index < pBits.length; index++) {
			if (pBits[index] == 1) {
				lBits[index]++;
			}
		}
		letters[correctIndex].setBits(lBits);
	}

	public void punish(int correctIndex, Letter pattern) {
		int[] pBits = pattern.getBits();
		for (int index = 0; index < letters.length; index++) {
			if (index != correctIndex) {
				int[] lBits = letters[index].getBits();
				for (int count = 0; count < lBits.length; count++) {
					if (pBits[index] == 0) {
						lBits[index]--;
					}
				}
				letters[index].setBits(lBits);
			}
		}
	}

	public int compareTo(Object obj) {
		return 0;
	}
}