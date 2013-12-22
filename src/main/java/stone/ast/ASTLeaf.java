package stone.ast;

import java.util.ArrayList;
import java.util.Iterator;

import stone.env.Env;
import stone.lexer.StoneException;
import stone.lexer.Token;

public class ASTLeaf extends ASTNode {
	private static ArrayList<ASTNode> _empty = new ArrayList<ASTNode>();
	protected Token _token;

	public ASTLeaf(Token token) {
		_token = token;
	}

	@Override
	public ASTNode child(int i) {
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int numChildren() {
		return 0;
	}

	@Override
	public Iterator<ASTNode> children() {
		return _empty.iterator();
	}

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

	@Override
	public Object evaluate(Env env) {
		throw new StoneException("cannot eval: " + toString(), this);
	}

}
