package divide;

public class Divide {
	/**
	 * Integer modulo/mod
	 * @param n
	 * @param m
	 * @return int
	 */
	private static int mod(int n, int m) {
		return calculate(n, m).getMod();
	}
	
	/**
	 * Integer quotient
	 * @param n
	 * @param m
	 * @return int
	 */
	private static int quotient(int n, int m) {
		return calculate(n, m).getQuotient();
	}
	
	/**
	 * calculate
	 * @param n
	 * @param m
	 * @return DivideResult
	 */
	public static DivideResult calculate(int n, int m) throws IllegalArgumentException, RuntimeException {
		if (m == 0) throw new IllegalArgumentException("m cannot be zero!");
		
		int quotient = 0, remainder = n;
		if (n != 0) {
			boolean negative = (n < 0 && m > 0) || (n > 0 && m < 0);
			int absN = Math.abs(n), absM = Math.abs(m);
			
			while (absN >= absM) {
				// find the max shift number
				int shift = 0;
	            while (absN >= (absM << shift)) {
	                shift++;
	            }
	            // calculate
	            absN -= absM << (shift - 1);
	            quotient += 1 << (shift - 1);
			}
			// check value
			remainder = n < 0 ? -absN : absN;
			if (negative) quotient = -quotient;
		}
		
		return new DivideResult(quotient, remainder);
	}

	/**
	 * main Method
	 * @param args
	 */
	public static void main(String[] args) {
		int mvalue = Divide.mod(10, 3);
		System.out.println(mvalue);

		int qvalue = Divide.quotient(10, 3);
		System.out.println(qvalue);
	}

}
