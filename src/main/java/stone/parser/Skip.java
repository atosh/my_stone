package stone.parser;

import java.util.List;

import stone.ast.ASTNode;
import stone.lexer.Token;

public class Skip extends Leaf {

	protected Skip(String[] tokenStrings) {
		super(tokenStrings);
	}

	@Override
	protected void find(List<ASTNode> results, Token token) {
		// do nothing.
	}
}
