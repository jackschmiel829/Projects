package wordle;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
	
	String word;
	public static ArrayList<String> wordsList = new ArrayList<>();
	Random random = new Random();
	boolean won = false;
	
	
	public boolean loadWords(String filename) {
		if (filename == null) {
			return false;
		}
		try {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				if (scanner.hasNext()) {
					wordsList.add(scanner.next());
				}
			}
			scanner.close();
			return true;
		}catch(FileNotFoundException e) {
			System.err.print("File not found");
			return false;
		}
	}
	
	public void pickWord() {
		int num = random.nextInt(0, wordsList.size());
		word = wordsList.get(num);
	}

}
