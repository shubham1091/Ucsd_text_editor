package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	private MyLinkedList<String> shortList;
	private MyLinkedList<Integer> emptyList;
	private MyLinkedList<Integer> longerList;
	private MyLinkedList<Integer> list1;

	@Before
	public void setUp() {
		shortList = new MyLinkedList<>();
		shortList.add("A");
		shortList.add("B");

		emptyList = new MyLinkedList<>();

		longerList = new MyLinkedList<>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}

		list1 = new MyLinkedList<>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
	}

	@Test
	public void testGet() {
		assertEquals("Test failed for shortList.get(0)", "A", shortList.get(0));
		assertEquals("Test failed for shortList.get(1)", "B", shortList.get(1));

		try {
			emptyList.get(0);
			fail("IndexOutOfBoundsException not thrown for emptyList.get(0)");
		} catch (IndexOutOfBoundsException e) {
			// Expected behavior
		}

		try {
			shortList.get(-1);
			fail("IndexOutOfBoundsException not thrown for shortList.get(-1)");
		} catch (IndexOutOfBoundsException e) {
			// Expected behavior
		}

		try {
			shortList.get(2);
			fail("IndexOutOfBoundsException not thrown for shortList.get(2)");
		} catch (IndexOutOfBoundsException e) {
			// Expected behavior
		}
	}

	@Test
	public void testAddEnd() {
		shortList.add("C");
		assertEquals("Test failed for shortList.add(\"C\")", "C", shortList.get(2));

		emptyList.add(100);
		assertEquals("Test failed for emptyList.add(100)", 100, (int) emptyList.get(0));

		longerList.add(99);
		assertEquals("Test failed for longerList.add(99)", 99, (int) longerList.get(LONG_LIST_LENGTH));
	}

	@Test
	public void testAddAtIndex() {
		shortList.add(0, "C");
		assertEquals("Test failed for shortList.add(0, \"C\")", "C", shortList.get(0));
		assertEquals("Test failed for shortList.add(0, \"C\")", "A", shortList.get(1));

		shortList.add(2, "D");
		assertEquals("Test failed for shortList.add(2, \"D\")", "D", shortList.get(2));
		assertEquals("Test failed for shortList.add(2, \"D\")", "B", shortList.get(3));

		try {
			shortList.add(-1, "E");
			fail("IndexOutOfBoundsException not thrown for shortList.add(-1, \"E\")");
		} catch (IndexOutOfBoundsException e) {
			// Expected behavior
		}

		try {
			shortList.add(6, "E");
			fail("IndexOutOfBoundsException not thrown for shortList.add(4, \"E\")");
		} catch (IndexOutOfBoundsException e) {
			// Expected behavior
		}
	}

	@Test
	public void testRemove() {
		String removedElement = shortList.remove(0);
		assertEquals("Test failed for shortList.remove(0)", "A", removedElement);
		assertEquals("Test failed for shortList.remove(0)", "B", shortList.get(0));
		assertEquals("Test failed for shortList.remove(0)", 1, shortList.size());

		try {
			shortList.remove(-1);
			fail("IndexOutOfBoundsException not thrown for shortList.remove(-1)");
		} catch (IndexOutOfBoundsException e) {
			// Expected behavior
		}

		try {
			shortList.remove(1);
			fail("IndexOutOfBoundsException not thrown for shortList.remove(1)");
		} catch (IndexOutOfBoundsException e) {
			// Expected behavior
		}
	}

	@Test
	public void testSet() {
		String oldValue = shortList.set(0, "C");
		assertEquals("Test failed for shortList.set(0, \"C\")", "A", oldValue);
		assertEquals("Test failed for shortList.set(0, \"C\")", "C", shortList.get(0));

		try {
			shortList.set(-1, "D");
			fail("IndexOutOfBoundsException not thrown for shortList.set(-1, \"D\")");
		} catch (IndexOutOfBoundsException e) {
			// Expected behavior
		}

		try {
			shortList.set(2, "D");
			fail("IndexOutOfBoundsException not thrown for shortList.set(2, \"D\")");
		} catch (IndexOutOfBoundsException e) {
			// Expected behavior
		}

		try {
			shortList.set(0, null);
			fail("NullPointerException not thrown for shortList.set(0, null)");
		} catch (NullPointerException e) {
			// Expected behavior
		}
	}

	@Test
	public void testSize() {
		assertEquals("Test failed for shortList.size()", 2, shortList.size());
		assertEquals("Test failed for emptyList.size()", 0, emptyList.size());
		assertEquals("Test failed for longerList.size()", LONG_LIST_LENGTH, longerList.size());
	}
}
