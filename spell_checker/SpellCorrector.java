package spell_checker;

public class SpellCorrector implements ISpellCorrector
{
		


		/**
		 * Tells this <code>SpellCorrector</code> to use the given file as its dictionary
		 * for generating suggestions.
		 * @param dictionaryFileName File containing the words to be used
		 * @throws IOException If the file cannot be read
		 */
		public void useDictionary(String dictionaryFileName) throws IOException
		{
			Trie dict = new Trie();
			File file = new File(dictionaryFileName);
			Scanner s = new Scanner(file);
			while(s.hasNext())
			{
				dict.add(s.next());
			}
				
		}

		/**
		 * Suggest a word from the dictionary that most closely matches
		 * <code>inputWord</code>
		 * @param inputWord
		 * @return The suggestion or null if there is no similar word in the dictionary
		 */
		public String suggestSimilarWord(String inputWord)
		{
			
			
		}

	}

}
