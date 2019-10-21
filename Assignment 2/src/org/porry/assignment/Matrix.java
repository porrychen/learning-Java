/**
 * This is to create a Matrix
 * and to solve the Matrix
 * Addition and Multiplication
 */
package org.porry.assignment;

/**
 * @author porrychen
 *
 */
public class Matrix {
	private int M, N;
	private int[][] data;
	
	/**
	 * 
	 * @param M
	 * @param N
	 * @param randomValue
	 */
	public Matrix(int M, int N, boolean randomValue) {
		this.M = M;
		this.N = N;
		
		data = new int[M][N];
		if (randomValue) randomFill();
	}
	
	/**
	 * random fill 0 to 99
	 * @return true if M > 0 and N > 0
	 */
	private boolean randomFill() {
		if (M < 1 || N < 1) return false; 
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// cast double to int
				data[i][j] = (int) (Math.random() * 100);
			}
		}
		
		return true;
	}
	
	/**
	 * add two matrices
	 * @param A
	 * @param B
	 * @return a new matrix
	 */
	public static Matrix add(Matrix A, Matrix B) {
		if (A.getM() != B.getM() || A.getN() != B.getN()) {
			throw new RuntimeException("Matrix Addition needs the same M and N of two matrices!");
		}
		
		Matrix result = new Matrix(A.getM(), A.getN(), false);
		
		for (int i = 0; i < A.getM(); i++) {
			for (int j = 0; j < A.getN(); j++) {
				result.data[i][j] = A.data[i][j] + B.data[i][j];
			}
		}
		
		return result;
	}
	
	/**
	 * multiply two matrices
	 * @param A
	 * @param B
	 * @return a new matrix
	 */
	public static Matrix multiply(Matrix A, Matrix B) {
		if (A.getN() != B.getM()) {
			throw new RuntimeException("Matrix Multiplication needs that the N of A is equal to the M of B!");
		}
		
		Matrix result = new Matrix(A.getM(), B.getN(), false);
		
		for (int i = 0; i < result.M; i++) {
			for (int j = 0; j < result.N; j++) {
				for (int k = 0; k < A.N; k++)
					result.data[i][j] += A.data[i][k] * B.data[k][j];
			}
		}
		
		return result;
	}

	/**
	 * Get M value (row)
	 * @return
	 */
	public int getM() {
		return M;
	}

	/**
	 * Get N value (column)
	 * @return
	 */
	public int getN() {
		return N;
	}

	/**
	 * Get Matrix data
	 * @return
	 */
	public int[][] getData() {
		return data;
	}
	
}
