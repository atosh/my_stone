/**
 * 
 */
package stone.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import stone.ast.ASTLeaf;
import stone.ast.ASTNode;
import stone.lexer.Lexer;
import stone.lexer.ParseException;

public class Parser {
	public static final String kFactoryName = "create";

	protected List<Element> _elements;
	protected Factory _factory;

	public Parser(Class<? extends ASTNode> type) {
		reset(type);
	}

	protected Parser(Parser parser) {
		_elements = parser._elements;
		_factory = parser._factory;
	}

	public ASTNode parse(Lexer lexer) throws ParseException {
		ArrayList<ASTNode> results = new ArrayList<ASTNode>();
		for (Element element : _elements) {
			element.parse(lexer, results);
		}
		return _factory.make(results);
	}

	public boolean match(Lexer lexer) throws ParseException {
		if (_elements.size() == 0) {
			return true;
		}
		Element element = _elements.get(0);
		return element.match(lexer);
	}

	public Parser reset() {
		_elements = new ArrayList<Element>();
		return this;
	}

	public Parser reset(Class<? extends ASTNode> type) {
		_elements = new ArrayList<Element>();
		_factory = Factory.getForASTList(type);
		return this;
	}

	public static Parser rule() {
		return rule(null);
	}

	public static Parser rule(Class<? extends ASTNode> type) {
		return new Parser(type);
	}

	public Parser number() {
		return number(null);
	}

	public Parser number(Class<? extends ASTLeaf> type) {
		_elements.add(new NumberToken(type));
		return this;
	}

	public Parser identifier(HashSet<String> reserved) {
		return identifier(null, reserved);
	}

	public Parser identifier(Class<? extends ASTLeaf> type,
			HashSet<String> reserved) {
		_elements.add(new IdToken(type, reserved));
		return this;
	}

	public Parser string() {
		return string(null);
	}

	public Parser string(Class<? extends ASTLeaf> type) {
		_elements.add(new StringToken(type));
		return this;
	}

	public Parser token(String... strings) {
		_elements.add(new Leaf(strings));
		return this;
	}

	public Parser sep(String... strings) {
		_elements.add(new Skip(strings));
		return this;
	}

	public Parser ast(Parser parser) {
		_elements.add(new Tree(parser));
		return this;
	}

	public Parser or(Parser... parsers) {
		_elements.add(new OrTree(parsers));
		return this;
	}

	public Parser maybe(Parser parser) {
		Parser parser2 = new Parser(parser);
		parser2.reset();
		_elements.add(new OrTree(new Parser[] { parser, parser2 }));
		return this;
	}

	public Parser option(Parser parser) {
		_elements.add(new Repeat(parser, true));
		return this;
	}

	public Parser repeat(Parser parser) {
		_elements.add(new Repeat(parser, false));
		return this;
	}

	public Parser expr(Parser parser, Operators operators) {
		_elements.add(new Expr(null, parser, operators));
		return this;
	}

	public Parser expr(Class<? extends ASTNode> type, Parser parser,
			Operators operators) {
		_elements.add(new Expr(type, parser, operators));
		return this;
	}

	public Parser insertChoice(Parser parser) {
		Element element = _elements.get(0);
		if (element instanceof OrTree) {
			((OrTree) element).insert(parser);
		} else {
			Parser otherwise = new Parser(this);
			reset(null);
			or(parser, otherwise);
		}
		return this;
	}
}
