package stone.ast.parser;

import java.util.List;

import stone.Lexer;
import stone.ParseException;
import stone.ast.ASTNode;

public class Tree extends Element {

	private Parser _parser;

	protected Tree(Parser parser) {
		_parser = parser;
	}

	@Override
	protected void parse(Lexer lexer, List<ASTNode> results) throws ParseException {
		results.add(_parser.parse(lexer));
	}

	@Override
	protected boolean match(Lexer lexer) throws ParseException {
		return _parser.match(lexer);
	}

}
