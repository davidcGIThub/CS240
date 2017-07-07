package hangman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Math;

public class wordOptions 
{
	public class BadInputException extends Exception
	{
		private char let;
		
		public BadInputException(char let)
		{
			this.let = let;
		}
		
		public char getChar()
		{
			return let;
		}
	}
	
	private Set<String> words = new HashSet<String>();
	//private Set<String>[] wordList;
	private int length;
	private int guesses;
	private usedLetters letters;
	
	public wordOptions(String fileName, int len, int guess)
	{
		try
		{
			length = len;
			guesses = guess;
			letters = new usedLetters(len);
		
			File file = new File(fileName);
			Scanner s = new Scanner(file);
			while(s.hasNext())
			{
				String temp = s.next();
				if(checkWord(temp))
				{
					if(temp.length() == length)
					{
						words.add(temp.toLowerCase());
					}
				}
			}
		}
		catch (FileNotFoundException e) 
		{
	       		 e.printStackTrace();
		}
		
	}
	
	public int checkLetter(char letter) throws BadInputException
	{
		if((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z'))
		{
			int pos  = (int) Math.pow(2,length);
			int position = pos + 1;
			int frequency = 0;
	
			ArrayList<Set<String>> wordList = new ArrayList<Set<String>>(pos);
			for(int j = 0; j<pos; j++)
			{
				Set<String> w = new HashSet<String>();
		        	wordList.add(w);
			}
			
			Iterator<String> it = words.iterator();
		     while(it.hasNext())
		     {
		        String temp = it.next();
		        int value = 0;
		        for(int i = 0; i < temp.length(); i++) //find type of word temp is (bitwise value)
		        {
		        	if(letter == temp.charAt(i))
		        	{
		        		int place = 1 << ((temp.length()-1) - i);
		        		value = value + place;
		        	}
		        }
		        wordList.get(value).add(temp);  // add word to set at that type
		        if(wordList.get(value).size() > frequency)
		        {
		        	position = value;
		        	frequency = wordList.get(value).size();
		        }
		        else if(wordList.get(value).size() == frequency)
		        {
		        	if(value < position)
		        	{
		        		position = value;
		        	}
		        }
		     }
		     
		     if(position > 0)
		     {
		    	 letters.guess(letter,position);
		     }
		     else
		     {
			 guesses--;
		     }
		     
		     words = wordList.get(position);
		     return frequency;
		}
		else
		{
			throw new BadInputException(letter);
		}
		
	}
	
	public boolean checkWord(String token)
	{
		boolean b = true;
		for(int i = 0; i < token.length(); i++)
		{
			if((token.charAt(i) >= 'a' && token.charAt(i) <= 'z') || (token.charAt(i) >= 'A' && token.charAt(i) <= 'Z'))
			{
				
			}
			else
			{
				b = false;
				i = token.length();
			}
		}
		return b;
	}
	
	public int getGuesses()
	{
		return guesses;
	}
	
	public Set<String> getWords()
	{
		return words;
	}
	
	public usedLetters getLetters()
	{
		return letters;
	}
	
	@Override
	public String toString()
	{		
		String str = "You have " + guesses + " left\n" ;
		str = str + letters.toString();
 		return str;
		
	}

}

