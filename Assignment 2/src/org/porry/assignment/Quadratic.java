/**
 * This is to solve 
 * a quadratic equation
 */
package org.porry.assignment;

/**
 * @author porrychen
 * Quadratic Class
 */
public class Quadratic {
	private Root p = null, q = null;
	
	// ================== Begin Nested Classes ================== //
	/**
	 * Complex is a nested class to handle complex root
	 */
	class Complex implements Root {
		private double real; // real number
		private double imaginary; // imaginary number
		
		/**
		 * constructor
		 * @param real
		 * @param imaginary
		 */
		Complex(double real, double imaginary) {
			this.real = real;
			this.imaginary = imaginary;
		}
		
		/**
		 * @return String
		 */
		public String toString() {
			return (real == 0 ? "" : real) + (imaginary == 0 ? "" : (imaginary <  0 ? (" - " + (-imaginary)) : (" + " + imaginary)) + "i");
		}
		
		/**
		 * add other complex root
		 * @param root
		 * @return Root
		 */
		public Root add(Root root) {
			Complex a = this, b = (Complex) root;
			double re = a.real + b.real;
			double im = a.imaginary + b.imaginary;
			
			return new Complex(re, im);
		}
		
		/**
		 * multiply other complex root
		 * @param root
		 * @return Root
		 */
		public Root multiply(Root root) {
			Complex a = this, b = (Complex) root;
			double re = a.real * b.real - a.imaginary * b.imaginary;
			double im = a.real * b.imaginary + a.imaginary * b.real;
			
			return new Complex(re, im);
		}
		
		/**
		 * get real part
		 * @return double
		 */
		public double getReal() {
			return real;
		}
		
		/**
		 * get imaginary part
		 * @return double
		 */
		public double getImaginary() {
			return imaginary;
		}
	}
	
	/**
	 * Real is a nested class to handle real root
	 */
	class Real implements Root {
		private double real;
		
		/**
		 * constructor
		 * @param real
		 */
		Real(double real) {
			this.real = real;
		}
		
		/**
		 * @return String
		 */
		public String toString() {
			return real + "";
		}
		
		/**
		 * add other real root
		 * @param root
		 * @return Root
		 */
		public Root add(Root root) {
			return new Real(real + ((Real) root).getReal());
		}
		
		/**
		 * multiply other real root
		 * @param root
		 * @return Root
		 */
		public Root multiply(Root root) {
			return new Real(real * ((Real) root).getReal());
		}

		/**
		 * get real root
		 * @return double
		 */
		public double getReal() {
			return real;
		}		
	}
	// ================== End Nested Classes ================== //
	
	/**
	 * calculate three coefficients to get roots
	 * @param a
	 * @param b
	 * @param c
	 */
	public void calculate(double a, double b, double c) {
		p = q = null;
		if (a == 0) throw new RuntimeException("a is zero, so the expression is not a quadratic equation anymore.");
		
		// Compute the discriminant of the quadratic equation.
		double discriminant = Math.pow(b, 2) - 4 * a * c;
		
		// The discriminant is negative, so the quadratic equation has two complex solutions.
		if (discriminant < 0) {
			p = new Complex(-b / (2 * a), Math.sqrt(-discriminant) / (2 * a));
			q = new Complex(-b / (2 * a), -Math.sqrt(-discriminant) / (2 * a));
		} else if (discriminant == 0) {
			// There will be one real solution.
			p = q = new Real((-b + Math.sqrt(discriminant)) / (2 * a));
			
		} else {
			// There will result in two real solutions.
			p = new Real((-b + Math.sqrt(discriminant)) / (2 * a));
			q = new Real((-b - Math.sqrt(discriminant)) / (2 * a)); 
		}
	}
	
	/**
	 * sum two roots
	 * @return Root
	 */
	public Root sum() {
		if (p == null || q == null) return null;
		
		return p.add(q);
	}
	
	/**
	 * product two roots
	 * @return Root
	 */
	public Root product() {
		if (p == null || q == null) return null;
		
		return p.multiply(q);
	}
	
	/**
	 * check two double numbers are equal
	 * @param a
	 * @param b
	 * @return true if a == b
	 */
	public boolean equals(double a, double b) {
		if (a == b) return true;
		
		return Math.abs(a - b) < 0.0000001;
	}
	
	/**
	 * Get p value
	 * @return Root
	 */
	public Root getP() {
		return p;
	}
	
	/**
	 * Get q value
	 * @return Root
	 */
	public Root getQ() {
		return q;
	}
}
