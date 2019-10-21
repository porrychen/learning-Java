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
	private double p, q;
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public void calculator(double a, double b, double c) {
		p = q = 0;
		this.a = a;
		if (a == 0) throw new RuntimeException("a is zero, so the expression is not a quadratic equation anymore.");
		this.b = b;
		this.c = c;
		
		// Compute the discriminant of the quadratic equation.
		double discriminant = Math.pow(b, 2) - 4 * a * c;
		
		// The discriminant is negative, so the quadratic equation has two complex solutions.
		if (discriminant < 0) throw new RuntimeException("There are no real roots because the discriminant b*b − 4ac < 0!");
		
		if (discriminant == 0) {
			// There will be one real solution.
			p = q = (-b + Math.sqrt(discriminant)) / (2 * a);
			
		} else {
			// There will result in two real solutions.
			p = (-b + Math.sqrt(discriminant)) / (2 * a);
			q = (-b - Math.sqrt(discriminant)) / (2 * a); 
		}
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
		return equals(p + q, -b / a);
	}
	
	/**
	 * check p*q is equal to c / a
	 * @return true if equal
	 */
	public boolean checkMultiply() {
		return equals(p * q, c / a);
	}
	
	/**
	 * Get p value
	 * @return double
	 */
	public double getP() {
		return p;
	}
	
	/**
	 * Get q value
	 * @return double
	 */
	public double getQ() {
		return q;
	}
}
