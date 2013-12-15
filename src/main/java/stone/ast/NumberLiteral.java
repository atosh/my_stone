/**
 * 
 */
package stone.ast;

import stone.Token;

/**
 * @author toshi
 * 
 */
public class NumberLiteral extends ASTLeaf {
	/**
	 * 
	 */
	public NumberLiteral(Token token) {
		super(token);
	}

	public int value() {
		return token().getNumber();
	}
}
