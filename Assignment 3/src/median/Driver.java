package median;

public class Driver {
	public static void main(String[] args) {
		FindMedian findmedian = new FindMedian();
		
		Integer[] a = {-2, 5, 6, 9, 11};
		Integer[] b = {-5, 1, 8, 8, 19};
		
		int median = findmedian.getMedian(a, b);
		System.out.println("median = " + median);
	}

}
