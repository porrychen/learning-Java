package divide;

public class DivideResult {
	private final int quotient;
	private final int mod;
	
	DivideResult(int quotient, int mod) {
		this.quotient = quotient;
		this.mod = mod;
	}

	public int getQuotient() {
		return quotient;
	}

	public int getMod() {
		return mod;
	}
}
