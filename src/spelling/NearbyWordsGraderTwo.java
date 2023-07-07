package spelling;

import java.io.PrintWriter;
import java.util.List;

public class NearbyWordsGraderTwo {
    public static void main(String args[]) {
        int tests = 0;
        int incorrect = 0;
        String feedback = "";
        PrintWriter out;

        try {
            out = new PrintWriter("grader_output/module5.part2.txt");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            Dictionary d = new DictionaryHashSet();
            DictionaryLoader.loadDictionary(d, "test_cases/dict2.txt");
            NearbyWords nw = new NearbyWords(d);

            feedback += "** Test 1: 2 suggestions... ";
            tests++;
            List<String> d1 = nw.suggestions("dag", 4);
            if (d1.size() == 2) {
                feedback += "" + d1.size() + " suggestions returned.\n";
            } else {
                feedback += "Incorrect number of suggestions returned.\n";
                incorrect++;
            }

            feedback += "** Test 2: Checking suggestion correctness... ";
            feedback += "Suggestions: ";
            for (String i : d1) {
                feedback += i + ", ";
            }
            feedback += "\n";

            feedback += "** Test 3: 3 suggestions... ";
            tests++;
            d1 = nw.suggestions("fare", 3);
            if (d1.size() == 3) {
                feedback += "" + d1.size() + " suggestions returned.\n";
            } else {
                feedback += "Incorrect number of suggestions returned.\n";
                incorrect++;
            }

            feedback += "** Test 4: Checking suggestion correctness... ";
            feedback += "Suggestions: ";
            for (String i : d1) {
                feedback += i + ", ";
            }
            feedback += "\n";

        } catch (Exception e) {
            out.println(feedback + "Runtime error: " + e);
            out.close();
            return;
        }

        feedback += "Total tests: " + tests + ", Incorrect results: " + incorrect + "\n";
        feedback += "Tests complete. Make sure everything looks right.";

        out.println(feedback);
        out.close();
    }
}
