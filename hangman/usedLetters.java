
package hangman;

import java.util.Set;
import java.util.Iterator;
import java.util.TreeSet;

public class usedLetters 
{
	private int len;
	private Set<Character> letters;
	private char[] word;
	private int filled;
	
	
	public usedLetters(int len)
	{
		this.len = len;
		letters = new TreeSet<Character>();
		word = new char[len];
		filled = 0;
		for(int i = 0; i < word.length; i++)
		{
			word[i] = '-';
		}
	}
	
	public void guess(char letter, int position)
	{
		String place = Integer.toBinaryString(position);
		int diff = len - place.length();
		for(int i = 0; i < place.length(); i++)
		{
			if(place.charAt(i) == '1')
			{
				word[i+diff] = letter;
				filled++;
			}
		}
		letters.add(letter);
		
	}
	
	public boolean getFull()
	{
		if(filled >= len)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Set<Character> getLetters()
	{
		return letters;
	}
	
	@Override
	public String toString()
	{
		String str = "Used Letters: ";
		Iterator<Character> it = letters.iterator();
	    while(it.hasNext())
	    {
	    	str = str + it.next() + " ";
	    }
		str = str + "\nWord: ";
		for(int i = 0; i < word.length;i++)
	    {
	    		str = str + word[i];
	    }
		
		return str;
				
	/*	System.out.print("Used Letters: ");
		Iterator<Character> it = letters.iterator();
	    while(it.hasNext())
	    {
	    	System.out.print(it.next() + " ");
	    }
	    System.out.print("\nWord: ");
	    for(int i = 0; i < word.length;i++)
	    {
	    		System.out.print(word[i]);
	    }
	    System.out.print("\n");
	*/
	    
	}

}
