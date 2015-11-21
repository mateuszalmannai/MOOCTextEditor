/**
 *
 */
package spelling;

import java.util.List;

/**
 * @author Christine
 */
public interface AutoComplete {
  /**
   * @param prefix
   * @param numCompletions
   * @return List of completions
   * Return a list containing the numCompletions shortest legal completions of the prefix string.
   * All legal completions must be valid words in the dictionary.
   * If the prefix itself is a valid word, it should be included in the list of returned words.
   * The list of completions must contain all of the shortest completions, but when there are ties,
   * it may break them in any order. For example, if there the prefix string is "ste" and only the
   * words "step", "stem", "stew", "steer" and "steep" are in the dictionary, when the user asks for
   * 4 completions, the list must include "step", "stem" and "stew", but may include either the word
   * "steer" or "steep".
   */
  public List<String> predictCompletions(String prefix, int numCompletions);
}
