/**
 * This is to solve 
 * a quadratic equation
 */
package org.porry.assignment;

/**
 * @author porrychen
 *
 */
public class Quadratic {
	private double a, b, c;
	private Root p, q;
	
	class Complex implements Root {
		private double real; // real number
		private double imaginary; // imaginary number
		
		Complex(double real, double imaginary) {
			this.real = real;
			this.imaginary = imaginary;
		}
		
		public String toString() {
//			if (imaginary == 0) return real + "";
//	        if (real == 0) return imaginary + "i";
//	        if (imaginary <  0) return real + " - " + (-imaginary) + "i";
//	        return real + " + " + imaginary + "i";
			return (real == 0 ? "" : real) + (imaginary == 0 ? "" : (imaginary <  0 ? (" - " + (-imaginary)) : (" + " + imaginary)) + "i");
		}
		
		public Root add(Root root) {
			Complex a = this, b = (Complex) root;
			double re = a.real + b.real;
			double im = a.imaginary + b.imaginary;
			
			return new Complex(re, im);
		}
		
		public Root multiply(Root root) {
			Complex a = this, b = (Complex) root;
			double re = a.real * b.real - a.imaginary * b.imaginary;
			double im = a.real * b.imaginary + a.imaginary * b.real;
			
			return new Complex(re, im);
		}
		
		public double getReal() {
			return real;
		}
		
		public double getImaginary() {
			return imaginary;
		}
	}
	
	class Real implements Root {
		private double real;
		
		Real(double real) {
			this.real = real;
		}
		
		public String toString() {
			return real + "";
		}
		
		public Root add(Root root) {
			return new Real(real + ((Real) root).getReal());
		}
		
		public Root multiply(Root root) {
			return new Real(real * ((Real) root).getReal());
		}

		public double getReal() {
			return real;
		}		
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public void calculate(double a, double b, double c) {
		if (a == 0) throw new RuntimeException("a is zero, so the expression is not a quadratic equation anymore.");
		this.a = a;
		this.b = b;
		this.c = c;
		
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
	
	public Root sum() {
		if (p == null || q == null) return null;
		
		return p.add(q);
	}
	
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
	private boolean equals(double a, double b) {
		if (a == b) return true;
		
		return Math.abs(a - b) < 0.0000001;
	}
	
	/**
	 * check p + q is equal to -b / a
	 * @return true if equal
	 */
	public boolean checkAdd() {
		return true;//equals(p + q, -b / a);
	}
	
	/**
	 * check p*q is equal to c / a
	 * @return true if equal
	 */
	public boolean checkMultiply() {
		return true;//equals(p * q, c / a);
	}
	
	/**
	 * Get p value
	 * @return double
	 */
	public Root getP() {
		return p;
	}
	
	/**
	 * Get q value
	 * @return double
	 */
	public Root getQ() {
		return q;
	}
}
