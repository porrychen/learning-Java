/**
 * Test the Fourth Assignment
 */
package org.porry.assignment;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author porrychen
 * AssignmentTest Class
 */
public class AssignmentTest {
	/**
	 * Create and show Reminder Application
	 */
	private static void createAndShowGUI() {
		JFrame frame = new Reminder("Reminder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Automatically fit the window size
		frame.pack();
		// let the window at the center
		frame.setLocationRelativeTo(null);
		// show the window
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
