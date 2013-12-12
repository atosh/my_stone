/**
 * 
 */
package stone.ast;

import java.util.ArrayList;
import java.util.Iterator;

import stone.Token;

/**
 * @author toshi
 *
 */
public class ASTLeaf extends ASTNode {
	private static ArrayList<ASTNode> _empty = new ArrayList<ASTNode>();
	protected Token _token;
	public ASTLeaf(Token token) {
		_token = token;
	}
	
	/* (non-Javadoc)
	 * @see stone.ast.ASTNode#child(int)
	 */
	@Override
	public ASTNode child(int i) {
		throw new IndexOutOfBoundsException();
	}

	/* (non-Javadoc)
	 * @see stone.ast.ASTNode#numChildren()
	 */
	@Override
	public int numChildren() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see stone.ast.ASTNode#children()
	 */
	@Override
	public Iterator<ASTNode> children() {
		return _empty.iterator();
	}

	/* (non-Javadoc)
	 * @see stone.ast.ASTNode#location()
	 */
	@Override
	public String location() {
		return "at line " + _token.getLineNumber();
	}

	public String toString() {
		return _token.getText();
	}
	
	public Token token() {
		return _token;
	}
}
