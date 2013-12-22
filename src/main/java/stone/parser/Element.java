package stone.parser;

import java.util.List;

import stone.ast.ASTNode;
import stone.lexer.Lexer;
import stone.lexer.ParseException;

public abstract class Element {
	protected abstract void parse(Lexer lexer, List<ASTNode> results)
			throws ParseException;

	protected abstract boolean match(Lexer lexer) throws ParseException;
}
