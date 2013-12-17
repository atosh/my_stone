package stone.lexer;

import stone.ast.ASTNode;

public class StoneException extends RuntimeException {

	private static final long serialVersionUID = -2018486978320678709L;

	public StoneException(String string) {
		super(string);
	}

	public StoneException(String string, ASTNode astNode) {
		super(string + " " + astNode.location());
	}
}
