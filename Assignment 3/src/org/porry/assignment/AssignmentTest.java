/**
 * Test the Third Assignment
 */
package org.porry.assignment;

import checker.Checker;
import divide.Divide;
import divide.DivideResult;
import median.FindMedian;
import test.TestPalindrome;

/**
 * @author porrychen
 * AssignmentTest Class
 */
public class AssignmentTest {
	/**
	 * display an integer array
	 * @param a
	 */
	private void displayArray(Integer[] a) {
		for (int i = 0; i < a.length; i++) {
			if (i > 0) System.out.print(", ");
			System.out.print(a[i]);
		}
	}
	
	/**
	 * test each case
	 * @param num
	 * @param str
	 * @param a
	 * @param b
	 * @param n
	 * @param m
	 * @param exp
	 */
	private void showCase(int num, String str, Integer[] a, Integer[] b, int n, int m, String exp) {
		TestPalindrome testPalindrome = new TestPalindrome();
		FindMedian findMedian = new FindMedian();
		Checker checker = new Checker();
		
		System.out.println("\n-------------------- case " + num + " --------------------");
		
		System.out.println("Test Palindrome:");
		System.out.println(str + " is " + testPalindrome.isPalindrome(str) + "\n");
		
		System.out.println("Find Median:");
		System.out.print("a: [");
		displayArray(a);
		System.out.println("]");
		System.out.print("b: [");
		displayArray(b);
		System.out.println("]");
		System.out.println("The median is " + findMedian.getMedian(a, b) + ".\n");
		
		System.out.println("Get quotient and mod:");
		try {
			DivideResult divide = Divide.calculate(n, m);
			System.out.println(n + " / " + m + " = " + divide.getQuotient());
			System.out.println(n + " % " + m + " = " + divide.getMod() + "\n");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + "\n");
		}
		
		System.out.printf("Check Balance: %30s %20s \n", "without composition ", "with composition");
		System.out.printf(exp + "%30s %25s \n", checker.isBalanced(exp), checker.isValidExp(exp));
		System.out.println();
	}
	
	/**
	 * run
	 */
	private void run() {
		showCase(1, "", new Integer[]{-2, 5, 6, 9, 11}, new Integer[]{-5, 1, 8, 8, 19}, 10, 3, "()}[)(]{");
		
		showCase(2, "A MOM A", new Integer[]{}, new Integer[]{-1, 0, 1, 2, 2}, -200, -3, "[()]{}{[()()]()}");
		
		showCase(3, "MOON ", new Integer[]{4, 16, 20, 22, 23, 24}, new Integer[]{1, 2, 3, 5}, 2, 0, "()]");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("================================================");
		System.out.println("========= This is the third assignment =========");
		System.out.println("================================================");
		
		AssignmentTest test = new AssignmentTest();
		try {
			test.run();
		} finally {
			System.out.println("------------------------------------------------");
			System.out.println("                       Bye!");
		}
	}
}
