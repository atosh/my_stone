package stone.parser;

import java.util.List;

import stone.ast.ASTLeaf;
import stone.ast.ASTNode;
import stone.lexer.Lexer;
import stone.lexer.ParseException;
import stone.lexer.Token;

public abstract class AbstractToken extends Element {
	private Factory _factory;

	protected AbstractToken(Class<? extends ASTLeaf> type) {
		if (type == null) {
			type = ASTLeaf.class;
		}
		_factory = Factory.get(type, Token.class);
	}

	protected void parse(Lexer lexer, List<ASTNode> results) throws ParseException {
		Token token = lexer.read();
		if (test(token)) {
			ASTNode leaf = _factory.make(token);
			results.add(leaf);
		} else {
			throw new ParseException(token);
		}
	}

	protected boolean match(Lexer lexer) throws ParseException {
		return test(lexer.peek(0));
	}

	protected abstract boolean test(Token token);
}
