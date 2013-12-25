package stone.parser;

import stone.ast.ASTNode;
import stone.lexer.Lexer;
import stone.lexer.ParseException;

public interface IParser {
	public abstract ASTNode parse(Lexer lexer) throws ParseException;
}
