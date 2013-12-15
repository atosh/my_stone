package stone;

import static org.junit.Assert.*;

import org.junit.Test;

import stone.ast.ASTNode;

public class BasicParserTest {

	@Test
	public void test() throws ParseException {
		Lexer lexer = new Lexer(new CodeDialog());
		BasicParser parser = new BasicParser();
		while (lexer.peek(0) != Token.kEOF) {
			ASTNode node = parser.parse(lexer);
			System.out.println("=> " + node.toString());
		}
	}

}
