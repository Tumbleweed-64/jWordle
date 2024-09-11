import java.util.*;
import java.io.*;

public class WordleClone {
        static String ANSI_GREEN = "\u001B[0;32m";
	static String ANSI_YELLOW = "\u001B[0;33m";
	static String ANSI_RESET = "\u001B[0m";

	public static void main(String args[]) throws FileNotFoundException {
		Random rand = new Random();
		// validWords.txt contains all the words from the dictionary file that I grepped to make them valid (5 letters, no apostrophe)
		Scanner wordGetter = new Scanner(new File("./validWords.txt"));
		Scanner guessGetter = new Scanner(System.in);
		ArrayList<String> lines = new ArrayList<String>();
		String guess = "";
		String tmpWordHolder = "";
		boolean correct = false;
		int currGuess = 0;

		// Initializes the game board
		String[][] gameWords = {
			{" ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " "}
		};

		// Lump in all 4747 words from the valid dictionary file, probably not too good for the CPU, but who cares
		int dictIter = 0; // not wasting a good variable name like "i" on a while loop
		while (wordGetter.hasNextLine()) {
			lines.add(wordGetter.nextLine());
			dictIter++;
		}

		String selectedWord = lines.get(rand.nextInt(lines.size())).toLowerCase();

		// debug/cheat line, prints word
		// System.out.println("Randomly selected word is: " + selectedWord);

		while ((!correct) && (currGuess != 6)) {
			System.out.println("  WORDLE");
			System.out.print("+---------+\n");
			for (int displayIter = 0; displayIter < gameWords.length; displayIter++) {
				for (int i = 0; i < gameWords[0].length; i++) {
					System.out.print("|" + gameWords[displayIter][i]);
				}
				System.out.println("|");
			}
			System.out.print("+---------+\n\n");

			guess = (guessGetter.nextLine()).toLowerCase();

			if (guess.equals(selectedWord)) {
				System.out.print("\n\n\n\n\n");
				System.out.println(ANSI_GREEN + selectedWord + ANSI_RESET);
				System.out.println("You guessed the word in " + currGuess + " tries!");
				correct = true;
				break;
			}
			else {
				for (int j = 0; j < gameWords[0].length; j++) {
					String currChar = Character.toString(guess.toCharArray()[j]);

					if (guess.toCharArray()[j] == selectedWord.toCharArray()[j]) {
						gameWords[currGuess][j] = ANSI_GREEN + currChar + ANSI_RESET;
					}
					else if (selectedWord.contains(currChar)) {
						gameWords[currGuess][j] = ANSI_YELLOW + currChar + ANSI_RESET;
					}
					else {
						gameWords[currGuess][j] = currChar;
					}
				}
				System.out.println();
			}

			currGuess++;
		}

		if (correct) {
				System.out.println("\nGreat job! Play again soon!");
		}
		else {
				System.out.println("You lost. The correct word was" + selectedWord + ".");
				System.out.println("\nYou'll get it next time! Try again soon!");
		}
	}
}
