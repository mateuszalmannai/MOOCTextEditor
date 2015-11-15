package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    wordList = new LinkedList<>();
    starter = "";
    wordList.add(new ListNode(starter));
    rnGenerator = generator;
  }


  /**
   * Train the generator by adding the sourceText
   */
  @Override
  public void train(String sourceText) {

    // finding words
    final ArrayList<String> words = new ArrayList<>();
    final Pattern wordSplitter = Pattern.compile("[a-zA-Z.!?,;]+");

    final Matcher matcher = wordSplitter.matcher(sourceText);
    while (matcher.find()) {
      words.add(matcher.group());
    }



    ListNode currentNode = null;
    // set starter to be an empty String ""
    starter = "";

    for (String word : words) {
      // check if node exists
      boolean newWord = true;
      for (ListNode node : wordList) {
        if (node.getWord().equals(starter)) { // if "starter" is a node in the list
          node.addNextWord(word);             // add word as a nextWord to the "starter" node
          newWord = false;
          break;
        }
      }
      if (newWord) {
        currentNode = new ListNode(starter);
        wordList.add(currentNode);
        currentNode.addNextWord(word);
      }
      starter = word;            // set starter = w
    }

    if (currentNode == null) {
      currentNode = new ListNode(null);
    }
    currentNode.addNextWord(""); // add "" to be a next word for the last word in the source text.

  }

  /**
   * Generate the number of words requested.
   */
  @Override
  public String generateText(int numWords) {
    starter = "";                 // set "starter" to be an empty String ""
    String output = "";           // set "output" to be ""
    int counter = 1;

    while (counter < numWords) {  // while you need more words
      ListNode currentNode = null;
      for (ListNode node : wordList) {
        if (node.getWord().equals(starter)) { // find the "node" corresponding to "starter" in the list
          currentNode = node;
          break;
        }
      }
      if (currentNode == null) {    // if that node does not exist
        for (ListNode node : wordList) {
          if (node.getWord().equals("")) { // find the "node" corresponding to the empty String ""
            currentNode = node;
            break;
          }
        }
      }
      // select a random word from the "wordList" for "node"
      // currentNode is null
      String nextWord = "";
      if (currentNode != null) {
        nextWord = currentNode.getRandomNextWord(rnGenerator);
      }
      output += " " + nextWord; //    add the random word to the "output"
      starter = nextWord;       //    set "starter" to the random word
      counter++;                //    increment number of words added to the list
    }
    return output;
  }


  // Can be helpful for debugging
  @Override
  public String toString() {
    String toReturn = "";
    for (ListNode n : wordList) {
      toReturn += n.toString();
    }
    return toReturn;
  }

  /**
   * Retrain the generator from scratch on the source text
   */
  @Override
  public void retrain(String sourceText) {
    wordList = new LinkedList<>();
    starter = "";
    wordList.add(new ListNode(starter));
    train(sourceText);
  }


  /**
   * This is a minimal set of tests.  Note that it can be difficult
   * to test methods/classes with randomized behavior.
   *
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
    nextWords = new LinkedList<>();
  }

  public String getWord() {
    return word;
  }

  public void addNextWord(String nextWord) {
    nextWords.add(nextWord);
  }

  public String getRandomNextWord(Random generator) {
    // The random number generator should be passed from
    // the MarkovTextGeneratorLoL class
    if (nextWords.size() == 0) {
      return "";
    }
    final int index = generator.nextInt(nextWords.size());
    return nextWords.get(index);
  }

  public String toString() {
    String toReturn = word + ": ";
    for (String s : nextWords) {
      toReturn += s + "->";
    }
    toReturn += "\n";
    return toReturn;
  }

}


