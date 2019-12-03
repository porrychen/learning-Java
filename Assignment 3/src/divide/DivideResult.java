package divide;

/**
 * @author porrychen
 * DivideResult Class
 */
public class DivideResult {
	private final int quotient;
	private final int mod;
	
	/**
	 * constructor
	 * @param quotient
	 * @param mod
	 */
	DivideResult(int quotient, int mod) {
		this.quotient = quotient;
		this.mod = mod;
	}

	/**
	 * get quotient value
	 * @return int
	 */
	public int getQuotient() {
		return quotient;
	}

	/**
	 * get remainder value
	 * @return int
	 */
	public int getMod() {
		return mod;
	}
}
