package test;

public class TestPalindrome {
	/**
	 * Check if the string is a palindrome
	 * @param str
	 * @return boolean
	 */
	public boolean isPalindrome(String str) {
		int l = 0, r = str.length() - 1;
		
		while (l + 1 <= r) {
			if (str.charAt(l++) != str.charAt(r--)) return false;
		}
		
		return true;
	}

	/**
	 * main Method
	 * @param args
	 */
	public static void main(String[] args) {
		TestPalindrome testPalindrome = new TestPalindrome();
		
		boolean b1 = testPalindrome.isPalindrome("NOON");
		System.out.println("NOON" + " => " + b1);
		
		boolean b2 = testPalindrome.isPalindrome("NOWIWON");
		System.out.println("NOWIWON" + " => " + b2);
		
		boolean b3 = testPalindrome.isPalindrome("MOON");
		System.out.println("MOON" + " => " + b3);
	}

}
