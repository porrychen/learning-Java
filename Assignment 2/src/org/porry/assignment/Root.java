/**
 * It is an interface to 
 * deal with roots
 */
package org.porry.assignment;

/**
 * @author porrychen
 * Root Interface
 */
public interface Root {
	public String toString();
	
	public Root add(Root root);
	public Root multiply(Root root);
}
