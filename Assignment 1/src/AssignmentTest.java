import java.util.Scanner;

/*
 * It is the First Assignment.
 */

public class AssignmentTest {
	private static String input(Scanner scannerInput) {
		System.out.print("Please input a string: ");
		return scannerInput.nextLine();
	}
	
	public static void main(String[] args)
		throws java.io.IOException {
		
		// initial
		Menu menu = new Menu();
		Assignment assignment = new Assignment();
		Scanner scannerInput = new Scanner(System.in); 
		
		char choice, ignore;
		String inputValue = "";
		
		// loop
		for (;;) {
			do {
				menu.show();
				choice = (char) System.in.read();
				
				do {
					ignore = (char) System.in.read();
				} while (ignore != '\n');
				
			} while (!menu.isValid(choice));
			
			if (choice == 'q') break;
			System.out.println();
			
			// check and execute
			switch (choice) {
				case '0':
					inputValue = input(scannerInput); 
					
					break;
					
				case '4':
					inputValue = input(scannerInput);
				case '1':
					System.out.println("Original string is: " + inputValue);
					System.out.println("Reversed string is: " + assignment.reverse(inputValue));
					break;
				
				case '5':
					inputValue = input(scannerInput);
				case '2':
					System.out.println("Original string is: " + inputValue);
					System.out.println("Adjusted string is: " + assignment.adjust(inputValue));
					break;
				
				case '6':
					inputValue = input(scannerInput);
				case '3':
					System.out.println("Original string is: " + inputValue);
					System.out.println("Shuffled string is: " + assignment.shuffle(inputValue));
					break;
					
				default:
					// Three cases
					for (int i = 1; i <= 3; i++) {
						System.out.println();
						System.out.println("---------- begin test " + i + " ----------");
						inputValue = input(scannerInput); 
						System.out.println("  Original string is: " + inputValue);
						System.out.println();
						System.out.println("  Reversed string is: " + assignment.reverse(inputValue));
						System.out.println("  Adjusted string is: " + assignment.adjust(inputValue));
						System.out.println("  Shuffled string is: " + assignment.shuffle(inputValue));
						System.out.println("---------- end test " + i + " ----------");
					}
			}
			
			System.out.println();
		}

	}

}
