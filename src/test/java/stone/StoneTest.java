package stone;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import stone.ast.ASTNode;
import stone.ast.NullStatement;
import stone.env.BasicEnv;
import stone.env.IEnv;
import stone.env.NestedEnv;
import stone.env.ResizableArrayEnv;
import stone.lexer.Lexer;
import stone.lexer.ParseException;
import stone.lexer.StoneException;
import stone.lexer.Token;
import stone.parser.ArrayParser;
import stone.parser.BasicParser;
import stone.parser.ClassParser;
import stone.parser.ClosureParser;
import stone.parser.FuncParser;
import stone.parser.IParser;

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
		IParser parser = new BasicParser();
		IEnv env = new BasicEnv();
		interpret(parser, env);
	}

	@Test
	public void testFuncInterpreter() throws Exception {
		IParser parser = new FuncParser();
		IEnv env = new NestedEnv();
		interpret(parser, env);
	}

	@Test
	public void testClosureInterpreter() throws Exception {
		IParser parser = new ClosureParser();
		IEnv env = new NestedEnv();
		interpret(parser, env);
	}

	@Test
	public void testClassInterpreter() throws Exception {
		IParser parser = new ClassParser();
		IEnv env = new NestedEnv();
		interpret(parser, env);
	}

	@Test
	public void testArrayInterpreter() throws Exception {
		IParser parser = new ArrayParser();
		IEnv env = new NestedEnv();
		interpret(parser, env);
	}

	@Test
	public void testArrayEnvInterpreter() throws Exception {
		IParser parser = new ArrayParser();
		IEnv env = new ResizableArrayEnv();
		interpret(parser, env);
	}

	private void interpret(IParser parser, IEnv env) throws ParseException {
		try {
			Lexer lexer = new Lexer(_reader);
			while (lexer.peek(0) != Token.kEOF) {
				ASTNode node = parser.parse(lexer);
				if (!(node instanceof NullStatement)) {
					Object result = node.evaluate(env);
					System.out.println("=> " + result);
				}
			}
		} catch(StoneException e) {
			System.err.println(e.getMessage());
		}
	}
}
