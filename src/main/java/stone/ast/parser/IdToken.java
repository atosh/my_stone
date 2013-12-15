package stone.ast.parser;

import java.util.HashSet;

import stone.Token;
import stone.ast.ASTLeaf;

public class IdToken extends AbstractToken {

	private HashSet<String> _reserved;

	protected IdToken(Class<? extends ASTLeaf> type, HashSet<String> reserved) {
		super(type);
		_reserved = reserved != null ? reserved : new HashSet<String>();
	}

	@Override
	protected boolean test(Token token) {
		return token.isIdentifier() && !_reserved.contains(token.getText());
	}

}
