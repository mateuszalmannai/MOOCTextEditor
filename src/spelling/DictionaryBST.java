package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 */
public class DictionaryBST implements Dictionary {
  private TreeSet<String> dict;

  public DictionaryBST() {
    dict = new TreeSet<>();
  }

  /**
   * Add this word to the dictionary.  Convert it to lowercase first
   * for the assignment requirements.
   *
   * @param word The word to add
   * @return true if the word was added to the dictionary
   * (it wasn't already there).
   */
  public boolean addWord(String word) {
    boolean result = false;
    String lowerCase = word.toLowerCase();
    if (!isWord(lowerCase) && dict.add(lowerCase)) {
      result = true;
    }
    return result;
  }


  /**
   * Return the number of words in the dictionary
   */
  public int size() {
    return dict.size();
  }

  /**
   * Is this a word according to this dictionary?
   */
  public boolean isWord(String s) {
    boolean result = false;
    if (dict.contains(s.toLowerCase())) {
      result = true;
    }
    return result;
  }

}
