package algorithm;

import java.util.*;


public class Trie {
	private TrieNode root;
	public Trie() {
		root = new TrieNode();
	}

	public void insertNode(String word) {
		TrieNode curr = root;
		for (int i = 0, size = word.length(); i < size ; i++) {
			curr.child.put(word.charAt(i), curr = new TrieNode());
		}
		curr.isLastCharacter = true;
	}

	public boolean contains(String word) {
		TrieNode curr = root;
		for (int i = 0, size = word.length(); i < size ; i++) {
			curr = curr.child.get(word.charAt(i));
			if (curr == null)   return false;
		}
		return curr.isLastCharacter;
	}
}

class TrieNode {
	public Map<Character, TrieNode> child = new HashMap<>();
	boolean isLastCharacter;
}
