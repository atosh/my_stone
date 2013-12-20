package stone;

import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;

import stone.ast.ASTNode;
import stone.ast.NullStatement;
import stone.env.BasicEnvironment;
import stone.env.Environment;
import stone.env.NestedEnvironment;
import stone.lexer.Lexer;
import stone.lexer.ParseException;
import stone.lexer.Token;
import stone.parser.BasicParser;
import stone.parser.FuncParser;

public class StoneTest {

	private Reader _reader = new InputStreamReader(System.in);

	@Test
	public void testLexer() throws ParseException {
		Lexer lexer = new Lexer(_reader);
		for (Token token; (token = lexer.read()) != Token.kEOF; ) {
			System.out.println("=> " + token.getText());
		}
	}

	@Test
	public void testParser() throws ParseException {
		Lexer lexer = new Lexer(_reader);
		BasicParser parser = new BasicParser();
		while (lexer.peek(0) != Token.kEOF) {
			ASTNode node = parser.parse(lexer);
			System.out.println("=> " + node.toString());
		}
	}

	@Test
	public void testInterpreter() throws Exception {
		BasicParser parser = new BasicParser();
		Environment environment = new BasicEnvironment();
		Lexer lexer = new Lexer(_reader);
		while (lexer.peek(0) != Token.kEOF) {
			ASTNode node = parser.parse(lexer);
			if (!(node instanceof NullStatement)) {
				Object result = node.evaluate(environment);
				System.out.println("=> " + result);
			}
		}
	}

	@Test
	public void testFuncInterpreter() throws Exception {
		FuncParser parser = new FuncParser();
		Environment environment = new NestedEnvironment();
		Lexer lexer = new Lexer(_reader);
		while (lexer.peek(0) != Token.kEOF) {
			ASTNode node = parser.parse(lexer);
			if (!(node instanceof NullStatement)) {
				Object result = node.evaluate(environment);
				System.out.println("=> " + result);
			}
		}
	}
}
