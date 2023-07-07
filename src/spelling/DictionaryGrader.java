package spelling;

import java.io.PrintWriter;

public class DictionaryGrader {
    public static void main(String args[]) {
        PrintWriter out;
        try {
            out = new PrintWriter("grader_output/module4.part1.txt");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        int incorrect = 0;
        int tests = 0;
        String feedback = "";

        try {
            Dictionary dictLL = new DictionaryLL();

            feedback += "** Test #1: Adding new word to the LL dictionary...";
            if (dictLL.addWord("tEst")) {
                feedback += "addWord returned true.PASSED\n";
            } else {
                feedback += "addWord returned false. FAILED\n";
                incorrect++;
            }
            tests++;

            feedback += "** Test #2: Adding a second word...";
            dictLL.addWord("second");
            if (dictLL.size() == 2) {
                feedback += "Dictionary size is 2. PASSED\n";
            } else {
                feedback += "Dictionary size is incorrect. FAILED\n";
                incorrect++;
            }
            tests++;

            feedback += "** Test #3: Looking up word from first test...";
            if (dictLL.isWord("teSt")) {
                feedback += "isWord returned true.PASSED\n";
            } else {
                feedback += "isWord returned false. FAILED\n";
                incorrect++;
            }
            tests++;

            Dictionary dictBST = new DictionaryBST();

            feedback += "** Test #4: Adding a new word to the BST dictionary...";
            if (dictBST.addWord("tEst")) {
                feedback += "addWord returned true.PASSED\n";
            } else {
                feedback += "addWord returned false. FAILED\n";
                incorrect++;
            }
            tests++;

            feedback += "** Test #5: Adding second word to BST dictionary...";
            dictBST.addWord("second");
            if (dictBST.size() == 2) {
                feedback += "Dictionary size is 2. PASSED\n";
            } else {
                feedback += "Dictionary size is incorrect. FAILED\n";
                incorrect++;
            }
            tests++;

            feedback += "** Test #6: Retrieving the word from the first test...";
            if (dictBST.isWord("teSt")) {
                feedback += "isWord returned true. PASSED\n";
            } else {
                feedback += "isWord returned false. FAILED\n";
                incorrect++;
            }
            tests++;


            feedback += "** Test #7: Adding lots of words and retrieving some...";
            dictBST.addWord("seconds");
            dictBST.addWord("seconded");
            dictBST.addWord("secondhand");
            dictBST.addWord("selma");
            if (dictBST.isWord("seconded") && dictBST.isWord("selma")) {
                feedback += "isWord(seconded) and isWord(selma) returned true. PASSED\n";
            } else {
                feedback += "isWord check failed for some words. FAILED\n";
                incorrect++;
            }
            tests++;

            feedback += "** Test #8: Testing non-word in DictLL...";
            if (!dictLL.isWord("soup")) {
                feedback += "isWord(soup) returned false. PASSED\n";
            } else {
                feedback += "isWord(soup) returned true. FAILED\n";
                incorrect++;
            }
            tests++;

            feedback += "** Test #9: Testing non-word in DictBST...";
            if (!dictBST.isWord("soup")) {
                feedback += "isWord(soup) returned false. PASSED\n";
            } else {
                feedback += "isWord(soup) returned true. FAILED\n";
                incorrect++;
            }
            tests++;

        } catch (Exception e) {
            out.println("Runtime error: " + e);
            out.close();
            return;
        }

        feedback += "Tests complete. Make sure everything looks right.";

        feedback += "\nTotal tests: " + tests + "\n";
        feedback += "Incorrect tests: " + incorrect + "\n";
        feedback += "Accuracy: " + (tests - incorrect) * 100 / tests + "%\n";

        out.println(feedback);
        out.close();
    }
}
