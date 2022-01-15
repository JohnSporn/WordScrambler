/*
 * Play.java
 * 
 * A Program that displays a random word that the user needs to guess.
 * 
 * @Author John Sporn
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Play 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner indata = new Scanner(System.in);
		StringBuilder scrambled;
		List<String> words = new ArrayList<>();
		Random random = new Random();
		
		try 
		{ //Reading in a file of words and adding each word to the arraylist words
		BufferedReader file = new BufferedReader(new FileReader("C:\\users\\jms96\\Games\\WordScramble\\Words.txt"));
			String line = file.readLine();
			while((line = file.readLine()) != null) 
			{
				words.add(line);	
			}
			file.close();
		} 
		catch (IOException e) 
		{
			System.out.println("File not found");
		}
		
		System.out.println("--------------------");
		System.out.println("|The Word Scrambler|");
		System.out.println("--------------------");
		System.out.println();
		System.out.println("Directions: Guess the word! You only have one chance."
							+ " If you want help just type \"hint\". The hint will "
							+ "provide you with every other letter of the word.");
		System.out.println();
		
		for(int i = 0; i < words.size(); i++) 
		{
			String userInput = "";
			String word = words.get(random.nextInt(words.size()));
			scrambled = scrambledWord(word);
			
			while(userInput != word) //Game loop
			{
				System.out.println(scrambled);
				for(int j = 0; j < word.length(); j++) 
				{
					System.out.print("-");
				}
				System.out.println();
				
				userInput = indata.nextLine();
				System.out.println();
			
				if(word.equals(userInput))
				{
					System.out.println("You win!");
					break;
				} 	
				else if(userInput.equalsIgnoreCase("Hint"))
				{
					getPlayerHint(indata, word);
					System.out.println();
				} 
				else 
				{
					System.out.println("You Lose!");
					System.out.println("Correct answer is " + word);
					System.out.println();
					break;
				}	
			}
			
			String playAgain = "";
			System.out.println("Would you like to play again?");
			playAgain = indata.nextLine();
			System.out.println();
			while(!playAgain.equalsIgnoreCase("Yes") && !playAgain.equalsIgnoreCase("No")) 
			{
				System.out.println("Invalid input. Input 'yes' or 'no'");
				playAgain = indata.nextLine();
				System.out.println();
			}
			if(playAgain.equalsIgnoreCase("No"))
			{
				break;
			} 
			if(playAgain.equalsIgnoreCase("Yes"))
			{
				continue;
			} 
		}
		System.out.println("Goodbye");
	}

	private static void getPlayerHint(Scanner indata, String word) 
	{   //Outputs every other letter of the word			
		System.out.println("Hint:");
		for(int j = 0; j < word.length(); j+=2) 
		{
			System.out.print(word.charAt(j) + " ");
		}	
		System.out.println();		
	}
	
	private static StringBuilder scrambledWord(String word) 
	{	//Converts string to a sequence of characters, shuffles them, and then adds each character to a new string 
		List<Character> scramble = new ArrayList<>(word.length());
		StringBuilder newWord = new StringBuilder(word.length());	
		for(char c: word.toCharArray())
		{
			scramble.add(c);	
		}
		Collections.shuffle(scramble);
		for(Character ch: scramble) 
		{	
			newWord.append(ch);
		}
		return newWord;
	}
}
