package document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class BasicDocumentGrader {
  private static final String MOD1_TEST_CASES = "/Users/mateusz/IdeaProjects/MOOCTextEditor/test_cases/mod1TestCases.txt";
  private static final String MOD1_PART1_OUT = "/Users/mateusz/IdeaProjects/MOOCTextEditor/grader_output/module1.part1.out";
  private static final String MOD1_PART2_OUT = "/Users/mateusz/IdeaProjects/MOOCTextEditor/grader_output/module1.part2.out";

  public static void main(String[] args) {
    try {
      System.out.println("Sentences, words, and syllables:");
      BufferedReader br = new BufferedReader(new FileReader(MOD1_TEST_CASES));
      String line;
      PrintWriter out = new PrintWriter(MOD1_PART1_OUT, "utf-8");
      while ((line = br.readLine()) != null) {
        BasicDocument doc = new BasicDocument(line);
        String result = doc.getNumSentences() + " " + doc.getNumWords() + " " + doc.getNumSyllables() + " ";
        System.out.print(result);

        out.print(result);
      }
      out.print("\n");
      out.close();
      System.out.println("\nFlesch scores:");
      br.close();

      br = new BufferedReader(new FileReader(MOD1_TEST_CASES));
      out = new PrintWriter(MOD1_PART2_OUT, "utf-8");
      while ((line = br.readLine()) != null) {
        BasicDocument doc = new BasicDocument(line);
        String result = doc.getFleschScore() + " ";
        System.out.print(result);
        out.print(result);
      }
      out.print("\n");
      out.close();
      System.out.print('\n');
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
