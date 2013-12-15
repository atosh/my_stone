package stone.ast.parser;

import java.util.List;

import stone.Token;
import stone.ast.ASTNode;

public class Skip extends Leaf {

	protected Skip(String[] tokenStrings) {
		super(tokenStrings);
	}

	@Override
	protected void find(List<ASTNode> results, Token token) {
		// do nothing.
	}
}
