package spelling;

import java.util.*;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 * @author You
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

  private TrieNode root;
  private int size;


  public AutoCompleteDictionaryTrie() {
    root = new TrieNode();
  }


  /**
   * Insert a word into the trie.
   * For the basic part of the assignment (part 2), you should ignore the word's case.
   * That is, you should convert the string to all lower case as you insert it.
   */
  public boolean addWord(String word) {
    boolean result = true;
    TrieNode currentNode = root;
    for (char character : word.toLowerCase().toCharArray()) {
      TrieNode childNode = currentNode.getChild(character);
      if (childNode == null) {
        currentNode = currentNode.insert(character);
      } else {
        currentNode = childNode;
      }
    }
    if (currentNode.endsWord()) {
      result = false;
    } else {
      currentNode.setEndsWord(true);
    }

    return result;
  }

  /**
   * Return the number of words in the dictionary.  This is NOT necessarily the same
   * as the number of TrieNodes in the trie.
   */
  public int size() {
    return sizeChildren(root);
  }

  private int sizeChildren(TrieNode current) {
    int size = 0;

    if (current == null) {
      return 0;
    }

    TrieNode nextNode;
    for (Character character : current.getValidNextCharacters()) {
      nextNode = current.getChild(character);
      if (nextNode.endsWord()) {
        size++;
      }
      size = size + sizeChildren(nextNode);
    }
    return size;
  }


  /**
   * Returns whether the string is a word in the trie
   */
  @Override
  public boolean isWord(String s) {
    TrieNode currentNode = root;
    for (char character : s.toLowerCase().toCharArray()) {
      TrieNode node = currentNode.getChild(character);
      if (node == null) {
        return false;
      } else {
        currentNode = node;
      }
    }
    return (currentNode.endsWord());
  }

  /**
   * * Returns up to the n "best" predictions, including the word itself,
   * in terms of length
   * If this string is not in the trie, it returns null.
   *
   * @param prefix         The text to use at the word stem
   * @param numCompletions The maximum number of predictions desired.
   * @return A list containing the up to n best predictions
   */
  @Override
  public List<String> predictCompletions(String prefix, int numCompletions) {
    // This method should implement the following algorithm:
    // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    //    empty list
    // 2. Once the stem is found, perform a breadth first search to generate completions
    //    using the following algorithm:
    //    Create a queue (LinkedList) and add the node that completes the stem to the back
    //       of the list.
    //    Create a list of completions to return (initially empty)
    //    While the queue is not empty and you don't have enough completions:
    //       remove the first Node from the queue
    //       If it is a word, add it to the completions list
    //       Add all of its child nodes to the back of the queue
    // Return the list of completions

    // Think about the length of the words returned by breadth first search and how they will
    // be ordered

    LinkedList<TrieNode> queue = new LinkedList<>();
    ArrayList<String> completions = new ArrayList<>();

    TrieNode currentNode = getNode(prefix);

    if (currentNode == null) {
      return completions;
    }

    queue.add(currentNode);
    while (!queue.isEmpty() && completions.size() != numCompletions) {
      currentNode = queue.removeFirst();

      if (currentNode.endsWord()) {
        completions.add(currentNode.getText());
      }

      TrieNode temp = currentNode;
      currentNode.getValidNextCharacters().forEach(character -> queue.add(temp.getChild(character)));
    }

    return completions;
  }

  private TrieNode getNode(String prefix) {
    TrieNode currentNode = root;
    for (char character : prefix.toLowerCase().toCharArray()) {
      TrieNode nextNode = currentNode.getChild(character);
      if (nextNode == null) {
        return null;
      } else {
        currentNode = nextNode;
      }
    }
    return currentNode;
  }

  // For debugging
  public void printTree() {
    printNode(root);
  }

  /**
   * Do a pre-order traversal from this node down
   */
  public void printNode(TrieNode curr) {
    if (curr == null)
      return;

    System.out.println(curr.getText());

    TrieNode next = null;
    for (Character c : curr.getValidNextCharacters()) {
      next = curr.getChild(c);
      printNode(next);
    }
  }


}