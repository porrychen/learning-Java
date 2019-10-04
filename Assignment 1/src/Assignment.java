import java.util.Random;

/*
 * Reverse, adjust, and shuffle a String
 */

public class Assignment {
	private Random rand;
	
	Assignment() {
		rand = new Random();
	}
	
	// ========== Begin == reverse a string ==========
	/**
     * @param str: a String
     * @return: a new String
     */
	public String reverse(String str) {
		// convert to char array
		char[] result = str.toCharArray();
		
		// return a new string
		return new String(reverse(result));
	}
	
	/**
     * @param ch: a char array
     * @return: the same char array
     */
	public char[] reverse(char[] ch) {
		// swap values from left to right
		for (int left = 0, right = ch.length - 1; left < right; left++, right--) {
			swapAt(ch, left, right);
		}
		
		return ch;
	}
	// ========== End == reverse a string ==========
	
	// ========== Begin == adjust a string ==========
	public String adjust(String str) {
		char [] odd = new char[str.length() / 2 + 1];
		char [] even = new char[str.length() / 2];
		
		for (int left = 0, right = 1, i = 0; left < str.length(); i++, left += 2, right += 2) {
			odd[i] = str.charAt(left);
			if (right < str.length()) even[i] = str.charAt(right);
		}
		
		// return a new string
		return new String(new String(reverse(odd)) + new String(reverse(even)));
	}
	// ========== End == adjust a string ==========
	
	// ========== Begin == shuffle a string ==========
	public String shuffle(String str) {
		// convert to char array
		char[] ch = str.toCharArray();
		
		for (int i = 0; i < ch.length; i++) {
			swapAt(ch, i, rand.nextInt(ch.length - i) + i);
		}
		
		return new String(ch);
	}
	// ========== End == shuffle a string ==========
	
	private void swapAt(char[] ch, int i, int j) {
        char tmp = ch[i];
        ch[i] = ch[j];
        ch[j] = tmp;
    }
}
