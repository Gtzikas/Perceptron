package main;

import java.io.*;

public class PerceptronCharacterRecognition {
	public static void main(String[] args) throws FileNotFoundException {
		File trainingFile = new File("Training.dat");
		Perceptron analyze = Perceptron.getInstance();
		analyze.trainTemplates(trainingFile);
		/*
		 * File output = new File("Output.txt"); File recognitionFile = new
		 * File("Recognition.dat"); analyze.recognizeCharacters(recognitionFile,
		 * output);
		 */
	}
}