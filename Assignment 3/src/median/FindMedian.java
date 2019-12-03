package median;

public class FindMedian {
	/**
	 * find median of two sorted arrays.
	 * @param a
	 * @param b
	 * @return int
	 */
	public int getMedian(Integer[] a, Integer[] b) {
		int m = a.length, n = b.length;
		if (m > n) {
			// make sure that m is less than n even though test 1 said these two arrays are the same length 
			return getMedian(b, a);
		}
		
		int start = 0, end = m, half = (m + n + 1) / 2;
		// find the middle position that left part is smaller than the right part
		while (start <= end) {
			// the begin indexes of a and b in right part
			int partitionA = (start + end) / 2;
			int partitionB = half - partitionA;
			
			// 1. check the begin index of a is greater than start and   
            // the last element of a in the left part is less than the first element of b in the right part  
            // 2. check the begin index of a is less than end and   
            // the last element of b in left part is less than the first element of a in the right part 
			if (partitionA > start && a[partitionA - 1] > b[partitionB]) {
				end = partitionA - 1;
				continue;
			} else if (partitionA < end && b[partitionB - 1] > a[partitionA]) {
				start = partitionA + 1;
				continue;
			}
			
			// 3. find the max value in left part
			int maxLeft = partitionA == 0 ? b[partitionB - 1] : (partitionB == 0 ? a[partitionA - 1] : Math.max(a[partitionA - 1], b[partitionB - 1]));
			// the half variable already adds 1, so maxLeft will be the median value when the total length is odd
			// although we knew two arrays are the same length, just in case.
			if ((m + n) % 2 == 1) return maxLeft;
			
			// 4. find the min value in right part
			int minRight = partitionA == m ? b[partitionB] : (partitionB == n ? a[partitionA] : Math.min(a[partitionA], b[partitionB]));
			// get the median value
			return (maxLeft + minRight) / 2;
		}
		
		throw new IllegalArgumentException("Please make sure that two arrays are sorted!");
	}
}
