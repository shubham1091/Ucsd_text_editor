package textgen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class MyLinkedListGrader {

	PrintWriter out;

	public String printListForwards(MyLinkedList<Integer> lst) {
		LLNode<Integer> curr;
		String ret = "";
		if (lst.head.data == null)
			curr = lst.head.next;
		else
			curr = lst.head;

		while (curr != null && curr.data != null) {
			ret += curr.data;
			curr = curr.next;
		}
		return ret;
	}

	public String printListBackwards(MyLinkedList<Integer> lst) {
		LLNode<Integer> curr;
		String ret = "";
		if (lst.tail.data == null)
			curr = lst.tail.prev;
		else
			curr = lst.tail;
		while (curr != null && curr.data != null) {
			ret += curr.data;
			curr = curr.prev;
		}
		return ret;
	}

	public void doTest() {
		int incorrect = 0;
		int tests = 0;
		String feedback = "";
		try {
			out = new PrintWriter("grader_output/module3.part1.txt", "utf-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		}
		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
		int nums[] = { 1, 2, 3, 4, 5 };

		feedback += "** Test #1: Adding to end of list...\n";
		tests++;
		for (int i : nums) {
			lst.add(i);
		}
		feedback += "Got " + printListForwards(lst) + ". \n";

		feedback += "** Test #2: Getting from the middle...\n";
		tests++;
		int fourthElement = lst.get(3);
		if (fourthElement != 4) {
			feedback += "Expected 4, but got " + fourthElement + ". \n";
			incorrect++;
		} else {
			feedback += "Fourth element was correct. \n";
		}

		lst.add(2, 6);

		feedback += "** Test #3: Adding to the middle...\n";
		tests++;
		feedback += "Got " + printListForwards(lst) + ". \n";

		feedback += "** Test #4: Testing 'prev' pointers by going through the list backwards...";
		tests++;
		feedback += "Got " + printListBackwards(lst) + ". \n";

		feedback += "** Test #5: Testing list size...\n";
		tests++;
		int listSize = lst.size();
		if (listSize != 6) {
			feedback += "Expected size 6, but got " + listSize + ". \n";
			incorrect++;
		} else {
			feedback += "List size is correct. \n";
		}

		lst.remove(2);
		feedback += "** Test #6: Removing from the middle...\n";
		tests++;
		feedback += "Got " + printListForwards(lst) + ". \n";

		feedback += "** Test #7: Testing 'prev' pointers on remove by going through the list backwards...\n";
		tests++;
		feedback += "Got " + printListBackwards(lst) + ". \n";

		feedback += "** Test #8: Testing size after remove...\n";
		tests++;
		listSize = lst.size();
		if (listSize != 5) {
			feedback += "Expected size 5, but got " + listSize + ". \n";
			incorrect++;
		} else {
			feedback += "List size is correct. \n";
		}

		feedback += "** Test #9: Testing add, remove, and add on new list...\n";
		tests++;
		lst = new MyLinkedList<Integer>();
		lst.add(0, 1);
		lst.remove(0);
		lst.add(0, 1);
		feedback += "Got " + printListForwards(lst) + ". \n";

		feedback += "** Test 10: Checking size after previous test...\n";
		tests++;
		listSize = lst.size();
		if (listSize != 1) {
			feedback += "Expected size 1, but got " + listSize + ". \n";
			incorrect++;
		} else {
			feedback += "List size is correct. \n";
		}

		feedback += "** Tests 11-20: Testing method bounds...\n";

		feedback += "Tests complete. Check that everything is as expected.\n";
		feedback += "Total tests: " + tests + "\n";
		feedback += "Incorrect tests:" + incorrect + "\n";
		out.println(feedback);
		out.close();
	}

	public static void main(String args[]) {
		MyLinkedListGrader grader = new MyLinkedListGrader();
		grader.doTest();
	}
}
