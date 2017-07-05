package spell_checker;

public class Trie implements ITrie
{
	private Node root;
	private int wordCount;
	private int nodeCount;
	private String words;
	
	public Trie()
	{
		root = new Node('!');
		root.value = 0;
		wordCount = 0;
		nodeCount = 1;
		words ="";
	}

	/**
	 * Adds the specified word to the trie (if necessary) and increments the word's frequency count
	 * @param word The word being added to the trie
	 */
	
	public void add(String word); // maybe need to check if the string is longer than 1??
	{
		int step = 0;
		boolean done = false;
		Node node = root;
		
		
		while(!done)
		{
			char let = word.charAt(step);
			
			if(node[let] == null)
			{
				node[let] = new Node(let);
				nodeCount++;
			}
			
			if(step < (word.length()-1) )
			{
				node = node[let];
			}
			else
			{
				node[let].value++;
				done = true;
				
					if(node[let].value == 1)
					{
						wordCount++;
					}
			}
			step++;
		}
		
	}
	
	/**
	 * Searches the trie for the specified word
	 * @param word The word being searched for
	 * @return A reference to the trie node that represents the word,
	 * 			or null if the word is not in the trie
	 */
	
	public INode find(String word)
	{
		int step = 0;
		boolean done = false;
		Node node = root;
		
		
		while(!done)
		{
			char let = word.charAt(step);
			
			if(node[let] == null)
			{
				done = true;
				return null;
			}
			else
			{
				if(step < (word.length()-1) )
				{
					node = node[let];
					step++;
				}
				else
				{
					if(node[let].getWordCount() == 0)
					{
						return null;
					}
					else
					{
						return node[let];
					}
					done = true;
				}
			}
		
		}
	}
	
	/**
	 * Returns the number of unique words in the trie
	 * @return The number of unique words in the trie
	 */
	
	public int getWordCount()
	{
		return wordCount;
	}
	
	/**
	 * Returns the number of nodes in the trie
	 * @return The number of nodes in the trie
	 */
	
	public int getNodeCount()
	{
		return nodeCount;
	}
	
	@Override
	public String toString()
	{
		String word = "";
		for(int i = 0; i < 26; i++)
		{
			if(!(root['a'+i] == null))
			{
				word = word + root['a'+i].getLetter();
				if(root['a' + i].value > 0)
				{
					words = words + word + "\n";
				}
				toString(word, root['a'+i]);
			}
		}
		String wordList = words;
		words = "";
		return wordList;
	}
	
	public void toString(String word,Node nod)
	{
		for(int i = 0; i < 26; i++)
		{
			if(!(nod['a'+i] == null))
			{
				word = word + nod['a'+i].getLetter();
				if(nod['a' + i].value > 0)
				{
					words = words + word + "\n";
				}
				toString(word, nod['a'+i]);
			}
		}
	}
	
	
	
	@Override
	public int hashCode()
	{
		return (wordCount << 16)^nodeCount; 
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean equiv = true;
		for (int i = 0; i < 26; i++)
		{
			if(root['a' + i] == null)
			{
				if(o.root['a' + i] == null)
				{
					
				}
				else
				{
					equiv = false;
					i = 26;
				}
			}
			else
			{
				if(o.root['a' + i] == null)
				{
					equiv = false;
					i = 26;
				}
				else if(root['a' + i].equals(o.root['a' + i]))
				{
					this.equiv(root['a' + i],o.root['a' + i]);
				}
			}
					
		}
		return equiv;
	}
	
	public void equals(Node one, Node two)
	{
		for (int i = 0; i < 26; i++)
		{
			if(one['a' + i] == null)
			{
				if(two['a' + i] == null)
				{
					
				}
				else
				{
					equiv = false;
					i = 26;
				}
			}
			else
			{
				if(two['a' + i] == null)
				{
					equiv = false;
					i = 26;
				}
				else if(one['a' + i].equals(two['a' + i]))
				{
					this.equiv(one['a' + i],two['a' + i]);
				}
			}
					
		}
	}
	
	
	public class Node implements INode
	{
		private int value;
		private char letter;
		private Node[] node = new Node['a' + 26];
		
		public Node(char let)
		{
			this.letter = let;
			value = 0;
		}
		
		/**
		 * Returns the frequency count for the word represented by the node
		 */
		
		public int getValue()
		{
			return value;
		}
		
		/**
		 * Returns the char value for the character represented by the node
		 */
		
		public char getLetter();
		{
			return letter;
		}
		
		@Override
		public boolean equals(Object o)
		{
			if(this.value == o.getValue && this.letter == o.getLetter())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	
}

