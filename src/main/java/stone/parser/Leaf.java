package stone.parser;

import java.util.List;

import stone.ast.ASTLeaf;
import stone.ast.ASTNode;
import stone.lexer.Lexer;
import stone.lexer.ParseException;
import stone.lexer.Token;

public class Leaf extends Element {
	private String[] _tokenStrings;

	protected Leaf(String[] tokenStrings) {
		_tokenStrings = tokenStrings;
	}

	@Override
	protected void parse(Lexer lexer, List<ASTNode> results) throws ParseException {
		Token token = lexer.read();
		if (token.isIdentifier()) {
			for (String tokenString : _tokenStrings) {
				if (tokenString.equals(token.getText())) {
					find(results, token);
					return;
				}
			}
		}
		if (_tokenStrings.length > 0) {
			throw new ParseException(_tokenStrings[0] + " expected.", token);
		} else {
			throw new ParseException(token);
		}
	}

	protected void find(List<ASTNode> results, Token token) {
		results.add(new ASTLeaf(token));
	}

	@Override
	protected boolean match(Lexer lexer) throws ParseException {
		Token token = lexer.peek(0);
		if (token.isIdentifier()) {
			for (String tokenString : _tokenStrings) {
				if (tokenString.equals(token.getText())) {
					return true;
				}
			}
		}
		return false;
	}
}
