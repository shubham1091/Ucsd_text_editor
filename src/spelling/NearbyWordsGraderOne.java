package spelling;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class NearbyWordsGraderOne {
    public static void main(String args[]) {
        int tests = 0;
        int incorrect = 0;
        String feedback = "";
        PrintWriter out;

        try {
            out = new PrintWriter("grader_output/module5.part1.txt");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            Dictionary d = new DictionaryHashSet();
            DictionaryLoader.loadDictionary(d, "test_cases/dict.txt");
            NearbyWords nw = new NearbyWords(d);

            List<String> d1 = nw.distanceOne("word", true);

            feedback += "** Test 1: distanceOne list size... ";
            tests++;
            if (d1.size() == 3) {
                feedback += "distanceOne returned " + d1.size() + " words.\n";
            } else {
                feedback += "distanceOne returned incorrect number of words.\n";
                incorrect++;
            }

            feedback += "** Test 2: distanceOne words returned... ";
            for (String i : d1) {
                feedback += i + ", ";
            }
            feedback += "\n";

            feedback += "** Test 3: distanceOne list size (allowing non-words)... ";
            tests++;
            d1 = nw.distanceOne("word", false);
            if (d1.size() == 5) {
                feedback += "distanceOne with non-words returned " + d1.size() + " words.\n";
            } else {
                feedback += "distanceOne with non-words returned incorrect number of words.\n";
                incorrect++;
            }

            d1 = new ArrayList<String>();

            feedback += "** Test 4: deletions list size... ";
            tests++;
            nw.deletions("makers", d1, true);
            if (d1.size() == 9) {
                feedback += "deletions returned " + d1.size() + " words.\n";
            } else {
                feedback += "deletions returned incorrect number of words.\n";
                incorrect++;
            }

            feedback += "** Test 5: deletions words returned... ";
            feedback += "deletions returned: ";
            for (String i : d1) {
                feedback += i + ", ";
            }
            feedback += "\n";

            d1 = new ArrayList<String>();

            feedback += "** Test 6: insertions list size... ";
            tests++;
            nw.insertions("or", d1, true);
            if (d1.size() == 20) {
                feedback += "insertions returned " + d1.size() + " words.\n";
            } else {
                feedback += "insertions returned incorrect number of words.\n";
                incorrect++;
            }

            feedback += "** Test 7: insertions words returned... ";
            feedback += "insertions returned: ";
            for (String i : d1) {
                feedback += i + ", ";
            }
            feedback += "\n";

        } catch (Exception e) {
            out.println("Runtime error: " + e);
            out.close();
            return;
        }

        feedback += "Total tests: " + tests + ", Incorrect results: " + incorrect + "\n";
        feedback += "Tests complete. Check that everything looks right.";

        out.println(feedback);
        out.close();
    }
}
