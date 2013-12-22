package stone;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import stone.ast.ASTNode;
import stone.ast.NullStatement;
import stone.env.BasicEnv;
import stone.env.Env;
import stone.env.NestedEnv;
import stone.lexer.Lexer;
import stone.lexer.ParseException;
import stone.lexer.Token;
import stone.parser.BasicParser;
import stone.parser.ClassParser;
import stone.parser.ClosureParser;
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
		Env env = new BasicEnv();
		Lexer lexer = new Lexer(_reader);
		while (lexer.peek(0) != Token.kEOF) {
			ASTNode node = parser.parse(lexer);
			if (!(node instanceof NullStatement)) {
				Object result = node.evaluate(env);
				System.out.println("=> " + result);
			}
		}
	}

	@Test
	public void testFuncInterpreter() throws Exception {
		FuncParser parser = new FuncParser();
		Env env = new NestedEnv();
		Lexer lexer = new Lexer(_reader);
		while (lexer.peek(0) != Token.kEOF) {
			ASTNode node = parser.parse(lexer);
			if (!(node instanceof NullStatement)) {
				Object result = node.evaluate(env);
				System.out.println("=> " + result);
			}
		}
	}

	@Test
	public void testClosureInterpreter() throws Exception {
		ClosureParser parser = new ClosureParser();
		Env env = new NestedEnv();
		Lexer lexer = new Lexer(_reader);
		while (lexer.peek(0) != Token.kEOF) {
			ASTNode node = parser.parse(lexer);
			if (!(node instanceof NullStatement)) {
				Object result = node.evaluate(env);
				System.out.println("=> " + result);
			}
		}
	}

	@Test
	public void testClassInterpreter() throws Exception {
		String script =
				"class Point {\n"
				+ "x = 0\n"
				+ "}\n"
				+ "p = Point.new\n"
				+ "p.x = 10\n";
		Reader reader = new StringReader(script);
		ClassParser parser = new ClassParser();
		Env env = new NestedEnv();
		Lexer lexer = new Lexer(_reader);
		while (lexer.peek(0) != Token.kEOF) {
			ASTNode node = parser.parse(lexer);
			if (!(node instanceof NullStatement)) {
				Object result = node.evaluate(env);
				System.out.println("=> " + result);
			}
		}
	}

}
