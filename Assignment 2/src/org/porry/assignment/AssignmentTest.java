/**
 * Test the Second Assignment
 */
package org.porry.assignment;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author porrychen
 *
 */
public class AssignmentTest {
	/**
	 * display a Matrix
	 * @param A
	 */
	private void displayMatrix(Matrix A) {
		if (A.getM() == 0 || A.getN() == 0) {
			System.out.println("This matrix does not have data that can be shown!");
			return;
		}
		
		for (int i = 0; i < A.getM(); i++) {
			for (int j = 0; j < A.getN(); j++) {
				System.out.print("\t");
				System.out.print(A.getData()[i][j]);
			}
			
			System.out.println();
		}
	}
	
	/**
	 * run the test
	 */
	public void run() {
		Scanner scannerInput = new Scanner(System.in);
		try {
			int count = 0;
			System.out.print("How many test cases? Please input a number: ");
			for (;;) {
				try {
					count = scannerInput.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.out.print("Please input the correct number: ");
					scannerInput.next();
				}
			}
			
			// Test Quadratic
			Quadratic quadratic = new Quadratic();
			double a, b, c;
			Root p, q;
			
			System.out.println();
			System.out.println("=== Quadratic ===");
			for (int i = 0; i < count; i++) {
				try {
					System.out.println("----- case " + (i + 1) + " -----");
					System.out.print("Please input a number for \"a\": ");
					a = scannerInput.nextDouble();
					System.out.print("Please input a number for \"b\": ");
					b = scannerInput.nextDouble();
					System.out.print("Please input a number for \"c\": ");
					c = scannerInput.nextDouble();
					
					try {
						// calculate
						quadratic.calculate(a, b, c);
						// get roots
						p = quadratic.getP();
						q = quadratic.getQ();
						// display roots
						System.out.println("\n -b/a = " + (-b / a)); 
						System.out.println("p + q = (" + p + ") + (" + q + ") = " + quadratic.sum());
						System.out.println("==> p + q = -b/a \n");
						System.out.println(" c/a = " + (c / a));
						System.out.println("  pq = (" + p + ") * (" + q + ") = " + quadratic.product());
						System.out.println("==> pq = c/a \n");
					} catch (RuntimeException e) {
						System.out.println("\n  " + e.getMessage());
					}
				} catch (InputMismatchException e) {
					System.out.println("\n the datatype of this input does not match!");
					scannerInput.next();
				}
			}
			System.out.println();
			
			// Test Matrix
			Matrix A = null, B = null, C = null;
			int m, n;
			
			System.out.println("=== Matrix ===");
			for (int i = 0; i < count; i++) {
				System.out.println("----- case " + (i + 1) + " -----");
				
				System.out.print("Please input an integer for \"the row of A\": ");
				try {
					m = scannerInput.nextInt();
					System.out.print("Please input an integer for \"the column of A\": ");
					n = scannerInput.nextInt();
					A = new Matrix(m, n, true);
				} catch (InputMismatchException e) {
					System.out.println("\n the row or column of A does not get a correct integer!");
					scannerInput.next();
				}
				
				System.out.print("Please input an integer for \"the row of B\": ");
				try {
					m = scannerInput.nextInt();
					System.out.print("Please input an integer for \"the column of B\": ");
					n = scannerInput.nextInt();
					B = new Matrix(m, n, true);
				} catch (InputMismatchException e) {
					System.out.println("\n the row or column of B does not get a correct integer!");
					scannerInput.next();
				}
				
				
				if (A instanceof Matrix) {
					System.out.println("A: ");
					displayMatrix(A);
				}
				
				if (B instanceof Matrix) {
					System.out.println("B: ");
					displayMatrix(B);
				}
				
				if (!(A instanceof Matrix && B instanceof Matrix)) {
					System.out.println("A or B does not be initialized!");
					continue;
				}
				
				System.out.println("A + B: ");
				try {
					C = Matrix.add(A, B);
					displayMatrix(C);
				} catch (RuntimeException e) {
					System.out.println("\t" + e.getMessage());
				}
				
				System.out.println("A * B: ");
				try {
					C = Matrix.multiply(A, B);
					displayMatrix(C);
				} catch (RuntimeException e) {
					System.out.println("\t" + e.getMessage());
				}
			}
		} finally {
			scannerInput.close();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws java.io.IOException {
		try {
			AssignmentTest test = new AssignmentTest();
			test.run();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("----------------");
			System.out.println("  Bye!");
		}
	}
}
