/**
 * 
 */
package org.porry.assignment;

/**
 * @author porrychen
 *
 */
public interface Root {
	public String toString();
	
	public Root add(Root root);
	public Root multiply(Root root);
}
