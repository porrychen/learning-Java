/*
 * Menu for the Assignment
 */

public class Menu {
	public void show() {
		System.out.println("======= Menu =======");
		System.out.println("  0. Input a string");
		System.out.println("  1. Reverse string");
		System.out.println("  2. Adjust string");
		System.out.println("  3. Shuffle string");
		System.out.println("  4. Reverse a new string");
		System.out.println("  5. Adjust a new string");
		System.out.println("  6. Shuffle a new string");
		System.out.println("  7. Test All from new string");
		System.out.print("Please choose one (q to quit): ");
	}
	
	public boolean isValid(int ch) {
		if (ch < '0' | ch > '7' & ch != 'q')
			return false;
		else
			return true;
	}
}
