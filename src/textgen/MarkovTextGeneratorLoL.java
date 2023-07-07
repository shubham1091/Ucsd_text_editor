package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {
		String[] words = sourceText.split("\\s+");

		if (words.length == 0) {
			return;
		}

		String prevWord = starter;
		for (int i = 0; i < words.length; i++) {
			String currWord = words[i];
			ListNode prevNode = getNode(prevWord);
			prevNode.addNextWord(currWord);
			prevWord = currWord;
		}

		ListNode lastNode = getNode(prevWord);
		lastNode.addNextWord(starter);
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if (wordList.isEmpty() || numWords == 0) {
			return "";
		}

		String currWord = starter;
		StringBuilder output = new StringBuilder(currWord);
		int wordsGenerated = 1;

		while (wordsGenerated < numWords) {
			ListNode currNode = getNode(currWord);
			String randomNextWord = currNode.getRandomNextWord(rnGenerator);
			output.append(" ").append(randomNextWord);
			currWord = randomNextWord;
			wordsGenerated++;
		}

		return output.toString();
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		for (ListNode n : wordList) {
			toReturn.append(n.toString());
		}
		return toReturn.toString();
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		wordList.clear();
		starter = "";
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.
	private ListNode getNode(String word) {
		for (ListNode node : wordList) {
			if (node.getWord().equals(word)) {
				return node;
			}
		}
		ListNode newNode = new ListNode(word);
		wordList.add(newNode);
		return newNode;
	}

	/**
	 * This is a minimal set of tests. Note that it can be difficult
	 * to test methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, " +
				"You say stop, and I say go, go, go, " +
				"Oh no. You say goodbye and I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello. " +
				"I say high, you say low, " +
				"You say why, and I say I don't know. " +
				"Oh no. " +
				"You say goodbye and I say hello, hello, hello. " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello. " +
				"Why, why, why, why, why, why, " +
				"Do you say goodbye. " +
				"Oh no. " +
				"You say goodbye and I say hello, hello, hello. " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello. " +
				"You say yes, I say no, " +
				"You say stop and I say go, go, go. " +
				"Oh, oh no. " +
				"You say goodbye and I say hello, hello, hello. " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello, hello, hello, " +
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/**
 * Links a word to the next words in the list
 * You should use this class in your implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {
		if (nextWords.isEmpty()) {
			return "";
		}
		int randomIndex = generator.nextInt(nextWords.size());
		return nextWords.get(randomIndex);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(word).append(": ");
		for (String nextWord : nextWords) {
			sb.append(nextWord).append("->");
		}
		sb.append("\n");
		return sb.toString();
	}

}
