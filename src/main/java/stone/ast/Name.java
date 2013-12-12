/**
 * 
 */
package stone.ast;

import stone.Token;

/**
 * @author toshi
 *
 */
public class Name extends ASTLeaf {
	/**
	 * 
	 */
	public Name(Token token) {
		super(token);
	}
	
	public String name() {
		return token().getText();
	}
}
