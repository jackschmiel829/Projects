package wordle;

import java.util.Scanner;

public class Play {

	public static void main(String[] args) {
		
		Wordle wordle = new Wordle();
		wordle.loadWords("WORDS.txt");
		wordle.pickWord();
		System.out.println("Type in a five-letter word as a guess. A new line will be printed out.\n"
				+ "If the character in a given slot is _, then the character you put in that slot is not in the answer.\n"
				+ "If the character is your letter in lowercase, that letter is in the answer but in a different slot.\n"
				+ "If the character is your letter in uppercase, that letter is in the correct slot as it pertains to the answer.");
		Scanner newScanner = new Scanner(System.in);
		
		int tries = 1;
		while (tries <= 6) {
			System.out.println("Guess #" + tries);
			String currentWord = newScanner.next();
			StringBuffer currWord = new StringBuffer(currentWord);
			StringBuffer response = new StringBuffer();
			if (!validGuess(currentWord)) {
				System.out.println("Invalid guess. Please try again");
			}else if(currentWord.equals(wordle.word)){
				tries++;
				wordle.won = true;
				break;
			}else {
				tries++;
				getResponse(currWord, response, wordle.word);
				System.out.println(response);
			}
		}
		
		if (wordle.won) {
			int triesNum = tries - 1;
			System.out.println("You won! The word was " + wordle.word + ". It took you " + triesNum + " tries.");
		}else {
			System.out.println("You lost. The correct word was " + wordle.word + ".");
		}
		
		
		
		
		
		
		newScanner.close();
		
	}
	
	private static boolean validGuess(String guess) {
		for (String word : Wordle.wordsList) {
			if (guess.equals(word)) {
				return true;
			}
		}
		return false;
	}
	
	private static void getResponse(StringBuffer currWord, StringBuffer response, String word) {
		StringBuffer wordBuffer = new StringBuffer(word);
		for (int i = 0; i < 5; i++) {
			if (currWord.charAt(i) == wordBuffer.charAt(i)) {
				response.append(Character.toUpperCase(currWord.charAt(i)));
				wordBuffer.replace(i, i + 1, "*");
			}else {
				int counter = 0;
				for (int j = 0; j < 5; j++) {
					if (currWord.charAt(i) == wordBuffer.charAt(j)) {
						counter++;
						wordBuffer.replace(j, j + 1, "*");
					}
				}
				if (counter > 0) {
					response.append(currWord.charAt(i));
				}else {
					response.append('_');
				}
			}
		}
	}
	
	
	
}
