/**
 * 
 */
package stone.ast;

import stone.env.IEnv;
import stone.lexer.StoneException;
import stone.lexer.Token;

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

	@Override
	public Object evaluate(IEnv env) {
		Object value = env.get(name());
		if (value == null) {
			throw new StoneException("undefined name: " + name(), this);
		}
		return value;
	}

}
