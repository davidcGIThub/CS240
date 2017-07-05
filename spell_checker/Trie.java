

public class Trie implements ITrie
{
	private Node root;
	private int wordCount;
	private int nodeCount;
	private String words;
	boolean equiv;
	
	public Trie()
	{
		root = new Node('!');
		root.value = 0;
		wordCount = 0;
		nodeCount = 1;
		words ="";
		equiv = true;
	}

	/**
	 * Adds the specified word to the trie (if necessary) and increments the word's frequency count
	 * @param word The word being added to the trie
	 */
	
	public void add(String word) // maybe need to check if the string is longer than 1??
	{
		int step = 0;
		boolean done = false;
		Node nod = root;
		
		
		while(!done)
		{
			char let = word.charAt(step);
			
			if(nod.node[let] == null)
			{
				nod.node[let] = new Node(let);
				nodeCount++;
			}
			
			if(step < (word.length()-1) )
			{
				nod = nod.node[let];
			}
			else
			{
				nod.node[let].value++;
				done = true;
				
					if(nod.node[let].value == 1)
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
		Node nod = root;
		
		
		while(!done)
		{
			char let = word.charAt(step);
			
			if(nod.node[let] == null)
			{
				done = true;
				return null;
			}
			else
			{
				if(step < (word.length()-1) )
				{
					nod = nod.node[let];
					step++;
				}
				else
				{
					done = true;
					if(nod.node[let].getValue() == 0)
					{
						return null;
					}
					else
					{
						return nod.node[let];
					}
				}
			}
		
		}
		return null;
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
		String word;
		for(int i = 0; i < 26; i++)
		{
			word = "";
			if(!(root.node['a'+i] == null))
			{
				word = word + root.node['a'+i].getLetter();
				if(root.node['a' + i].value > 0)
				{
					words = words + word + "\n";
				}
				toString(word, root.node['a'+i]);
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
			if(!(nod.node['a'+i] == null))
			{
				word = word + nod.node['a'+i].getLetter();
				if(nod.node['a' + i].value > 0)
				{
					words = words + word + "\n";
				}
				toString(word, nod.node['a'+i]);
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
		Trie other = (Trie)o;
		if(other == null)
		{
			equiv = false;
		}
		else if(this == other)
		{
			
		}
		else
		{
			for (int i = 0; i < 26; i++)
			{
				if(root.node['a' + i] == null)
				{
					if(other.root.node['a' + i] == null)
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
					if(other.root.node['a' + i] == null)
					{
						equiv = false;
						i = 26;
					}
					else if(root.node['a' + i].equals(other.root.node['a' + i]))
					{
						root.node['a' + i].equals(other.root.node['a' + i]);
					}
				}
			}
					
		}
		boolean equivalent = equiv;
		equiv = true;
		return equivalent;
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
		
		public char getLetter()
		{
			return letter;
		}
		
		@Override
		public boolean equals(Object o)
		{
			Node other = (Node)o;
			for (int i = 0; i < 26; i++)
			{
				if(node['a' + i] == null)
				{
					if(other.node['a' + i] == null)
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
					if(other.node['a' + i] == null)
					{
						equiv = false;
						i = 26;
					}
					else if(node['a' + i].value == other.node['a' + i].value)
					{
						node.equals(other.node['a' + i]);
					}
				}
						
			}
			return equiv;
		}
	}

	
}

