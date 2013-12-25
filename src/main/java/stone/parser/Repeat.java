package stone.parser;

import java.util.List;

import stone.ast.ASTList;
import stone.ast.ASTNode;
import stone.lexer.Lexer;
import stone.lexer.ParseException;

public class Repeat extends Element {
	private Parser _parser;
	private boolean _onlyOnce;

	protected Repeat(Parser parser, boolean once) {
		_parser = parser;
		_onlyOnce = once;
	}

	@Override
	protected void parse(Lexer lexer, List<ASTNode> results) throws ParseException {
		while (_parser.match(lexer)) {
			ASTNode node = _parser.parse(lexer);
			if (node.getClass() != ASTList.class || node.numChildren() > 0) {
				results.add(node);
			}
			if (_onlyOnce) {
				break;
			}
		}

	}

	@Override
	protected boolean match(Lexer lexer) throws ParseException {
		return _parser.match(lexer);
	}
}
