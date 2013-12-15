package stone.ast.parser;

import java.util.List;

import stone.Lexer;
import stone.ParseException;
import stone.ast.ASTNode;

public abstract class Element {
	protected abstract void parse(Lexer lexer, List<ASTNode> results)
			throws ParseException;

	protected abstract boolean match(Lexer lexer) throws ParseException;
}
