/**
 * Write and Read the ReminderFile
 */
package org.porry.assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

/**
 * @author porrychen
 * ReminderFile Class
 */
public class ReminderFile {
	private String filename = "reminder.txt";
	private Hashtable<String, String> notes;
	
	public ReminderFile() {
		notes = new Hashtable<String, String>();
	}
	
	/*
	 * Read the file into a Hashtable
	 * @return boolean: false if cannot read the file
	 */
	@SuppressWarnings("unchecked")
	public boolean readFile() {
		File file = new File(filename);
		if (file.exists()) {
			try {
				FileInputStream in = new FileInputStream(file);
				ObjectInputStream objectIn = new ObjectInputStream(in);
				notes = (Hashtable<String, String>) objectIn.readObject();
				objectIn.close();
				in.close();
				return true;
			} catch (IOException e) {
				
			} catch (Exception e) {
				
			}
		}
		return false;
	}
	
	/**
	 * Write a Hashtable into the file
	 * @return boolean: false if cannot save Hashtable into the file
	 */
	public boolean writeFile() {
		File file = new File(filename);
		try {
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream objectOut = new ObjectOutputStream(out);
			objectOut.writeObject(notes);
			objectOut.close();
			out.close();
			return true;
		} catch (IOException e) {
			
		} catch (Exception e) {
			
		}
		return false;
	}

	/**
	 * Getter notes
	 * @return Hashtable
	 */
	public Hashtable<String, String> getNotes() {
		return notes;
	}
}
