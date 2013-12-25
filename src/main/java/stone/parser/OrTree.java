package stone.parser;

import java.util.List;

import stone.ast.ASTNode;
import stone.lexer.Lexer;
import stone.lexer.ParseException;

public class OrTree extends Element {

	private Parser[] _parsers;

	protected OrTree(Parser[] parsers) {
		_parsers = parsers;
	}

	@Override
	protected void parse(Lexer lexer, List<ASTNode> results) throws ParseException {
		Parser parser = choose(lexer);
		if (parser == null) {
			throw new ParseException(lexer.peek(0));
		} else {
			results.add(parser.parse(lexer));
		}
	}

	@Override
	protected boolean match(Lexer lexer) throws ParseException {
		return choose(lexer) != null;
	}

	protected Parser choose(Lexer lexer) throws ParseException {
		for (Parser parser : _parsers) {
			if (parser.match(lexer)) {
				return parser;
			}
		}
		return null;
	}

	protected void insert(Parser parser) {
		Parser[] newParsers = new Parser[_parsers.length + 1];
		newParsers[0] = parser;
		System.arraycopy(_parsers, 0, newParsers, 1, _parsers.length);
		_parsers = newParsers;
	}
}
