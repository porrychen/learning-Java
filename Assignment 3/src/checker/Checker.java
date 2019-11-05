/**
 * To check for parenthesis, braces, and square brackets
 */
package checker;

import java.util.Stack;

/**
 * @author porrychen
 * Checker Class
 */
public class Checker {
	/**
	 * match parenthesis, braces，and square brackets without composition
	 * @param exp
	 * @return boolean
	 */
	public boolean isBalanced(String exp) {
		Stack<Character> stack = new Stack<Character>();
		
		// use for each loop
		for (char ch: exp.toCharArray()) {
			if (stack.isEmpty()) {
				stack.push(ch);
				continue;
			}
			
			// if match, then pop, else push
			if (is_match(ch, stack.peek())) {
				stack.pop();
			} else {
				stack.push(ch);
			}
		}
		
		// return value
		return stack.isEmpty();
	}
	
	/**
	 * check two characters
	 * @param c1
	 * @param c2
	 * @return boolean
	 */
	private boolean is_match(char c1, char c2) {
		// look at ASCII, we know that ( is 40, ) is 41, [ is 91, ] is 93, { is 123, and } is 125
		return Math.abs(c2 - c1) <= 2;
	}
	
	/**
	 * match parenthesis, braces，and square brackets with composition
	 * @param exp
	 * @return boolean
	 */
	public boolean isValidExp(String exp) {
		Stack<Character> stack = new Stack<Character>();
		
		// use for each loop
		for (char ch: exp.toCharArray()) {
			switch (ch) {
				case '(':
					stack.push(')');
					break;
				case '[':
					stack.push(']');
					break;
				case '{':
					stack.push('}');
					break;
				default:
					// if not empty or not the same then return false
					if (stack.isEmpty() || stack.pop() != ch) return false;
					break;
			}
		}
		
		// also return value
		return stack.isEmpty();
	}
}
