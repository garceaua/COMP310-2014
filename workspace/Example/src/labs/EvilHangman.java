package labs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * In Evil Hangman, the computer maintains a list of every word in the
 * English language, then continuously pares down the word list to try
 * to dodge the player's guesses as much as possible.
 *
 * Details here:
 * http://nifty.stanford.edu/2011/schwarz-evil-hangman/Evil_Hangman.pdf
 */
public class EvilHangman extends Hangman {
	private String getWord = "";
	private int GuessRemaining;
	private char Guesses;
	private int WordNum =0;
	private int SecretString_length;
	private String[] Wordlist = new String[235000];
	private boolean GuessResult = false;
	
	EvilHangman() throws FileNotFoundException {
		super();
		File file = new File("resources/dictionary.txt");
		FileReader reader = new FileReader(file);
		Scanner scanner = new Scanner(reader);
		// TODO: build up a data structure here
		
		java.util.LinkedList<String>[] coolArray = new LinkedList[8]; 
		
		
		while (scanner.hasNextLine()) {
			//System.out.println(scanner.nextLine());
			String currentWord = scanner.nextLine();
			if ((currentWord.length()>=3) && (currentWord.length()<=10)) { 
				int Wordlength = currentWord.length();
				if (coolArray[Wordlength] == null) {
					coolArray[Wordlength] = new LinkedList<String>();
				}
				coolArray[Wordlength].add(currentWord);
 			}
		}
		
		// Word Length, put those words into word bank. Random # 3-10. # - 3. 
		java.util.LinkedList<String>[] WordBank = (LinkedList<String>[]) coolArray[WordNum].toArray();  
	}
	
	// TODO: extend existing methods to be evil
	
	
public String getgetWord() {
	return getWord;
}
public int numGuessRemaining() {
	return GuessRemaining;
}
	
public boolean EvilString (char ch) {
	int tempWordNum = 0;
	for (int i=0; i < WordNum; i++) {
		for (int j=0; j < SecretString_length; j++) {
			if (Wordlist[i].charAt(j) == ch) {
				break;
			} else
				if (j == SecretString_length - 1) {
					if (Wordlist[i].charAt(j) != ch) {
						tempWordNum++; 	

	
					}
			
			}
		}
	}

	String[] temp = new String[tempWordNum];
	int tempIndex = 0;
	for (int i =0; i < WordNum; i++) {
		for (int j = 0; j < SecretString_length; j++) {
		if (Wordlist[i].charAt(j) ==ch) {
			break;
		}else {
			if (j == SecretString_length -1) {
				if (Wordlist [i].charAt(j) != ch) {
					temp[tempIndex] = Wordlist[i];
					tempIndex++;
				}
			}		
		}
	}
}
	if (tempWordNum ==0) {
		getWord = Wordlist[0];
		return false;
	} else {
		getWord = temp[0];
		WordNum = tempWordNum;
		Wordlist = temp;
		return true;
	}
		
}

public boolean makeGuess (char ch) {
	GuessResult = false;
	Guesses = ch;
	if (!RepeatInput()) {
		if (EvilString(Guesses)) {
			GuessRemaining--;
			GuessResult = false;
		} else {
			GuessResult = true;
		}
	} 	
	return GuessResult;
}

public boolean RepeatInput() {
	return false;
}

}


