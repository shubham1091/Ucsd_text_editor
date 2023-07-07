package textgen;

import java.util.Random;
import java.util.HashMap;
import java.io.PrintWriter;

public class MarkovTextGeneratorGrader {
    private static final int LENGTH = 500;

    public static void main(String[] args) {
        try {
            MarkovTextGenerator gen = new MarkovTextGeneratorLoL(new Random());

            int incorrect = 0;
            int tests = 0;
            String feedback = "";

            feedback += "\n** Test 1: Generating text before training...";
            try {
                gen.generateText(20);
                feedback += "PASSED ";
            } catch (Exception e) {
                feedback += "FAILED";
                incorrect++;
            }
            tests++;

            gen.train("");
            feedback += "\n** Test 2: Generating text after training on an empty file...";
            try {
                gen.generateText(20);
                feedback += "PASSED ";
            } catch (Exception e) {
                feedback += "FAILED";
                incorrect++;
            }
            tests++;

            String input = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
            gen.retrain(input);
            String res = gen.generateText(LENGTH);

            feedback += "\nGenerator produced: " + res + "\n";

            String[] words = res.split("[\\s]+");
            feedback += "\n** Test #3: Checking requested generator word count...";
            feedback += "Your generator produced " + words.length + " words. ";
            tests++;

            HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();

            for (String w : words) {
                if (wordCounts.containsKey(w)) {
                    wordCounts.put(w, wordCounts.get(w) + 1);
                } else {
                    wordCounts.put(w, 1);
                }
            }

            feedback += "\n** Test #4: Testing specific word counts...";
            feedback += "'I' appeared " + wordCounts.get("I") + " times. ";
            tests++;

            boolean found = true;
            feedback += "\n** Test #5: Checking that every word is used at least once...";
            // Your implementation to check that every word is used at least once
            if (wordCounts.size() != words.length) {
                feedback += "Some words were not used. ";
                incorrect++;
                found = false;
            } else {
                feedback += "Done. ";
            }
            tests++;

            found = true;
            feedback += "\n** Test 6: Seeing if last word is always followed by first word...";
            // Your implementation to check if the last word is always followed by the first
            // word
            String lastWord = words[words.length - 1];
            String firstWord = words[0];
            if (!res.endsWith(firstWord) || !res.contains(lastWord + " " + firstWord)) {
                feedback += "Last word is not always followed by the first word. ";
                incorrect++;
                found = false;
            } else {
                feedback += "Done. ";
            }
            tests++;

            feedback += "\n** Test #7: Requesting zero words...";
            String s = gen.generateText(0);
            feedback += "Generator generated: " + s + ". ";
            tests++;

            gen.train("");
            res = gen.generateText(LENGTH);
            words = res.split("[\\s]+");
            int i = 0;
            feedback += "\n** Test #8: Running train() on a generator that has already been trained...";
            for (String w : words) {
                if (w.equals("I")) {
                    i++;
                }
            }
            feedback += "The word 'I' appears " + i + " times. ";
            tests++;

            gen.retrain("");
            feedback += "\n** Test #9: Testing retrain()...";
            s = gen.generateText(20);
            feedback += "Text generated: " + s + ". \n";
            tests++;

            feedback += "\nTests complete. Make sure everything looks right.\n";

            feedback += "Tests run: " + tests + "\n";
            feedback += "Tests passed: " + (tests - incorrect) + "\n";
            feedback += "Tests failed: " + (incorrect) + "\n";
            PrintWriter f = new PrintWriter("grader_output/module3.part2.txt");
            f.println(feedback);
            f.close();

            if (found) {
                System.out.println("All words were found.");
            } else {
                System.out.println("Some words were not found.");
            }

            return;
        } catch (Exception e) {
            System.out.println("Error during runtime: " + e);
        }
    }
}
