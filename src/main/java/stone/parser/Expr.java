package stone.parser;

import java.util.ArrayList;
import java.util.List;

import stone.ast.ASTLeaf;
import stone.ast.ASTNode;
import stone.lexer.Lexer;
import stone.lexer.ParseException;
import stone.lexer.Token;

public class Expr extends Element {
	private Factory _factory;
	private Operators _operators;
	private Parser _parser;

	protected Expr(Class<? extends ASTNode> type, Parser parser,
			Operators operators) {
		_factory = Factory.getForASTList(type);
		_operators = operators;
		_parser = parser;
	}

	@Override
	protected void parse(Lexer lexer, List<ASTNode> results)
			throws ParseException {
		ASTNode right = _parser.parse(lexer);
		Precedence precedence;
		while ((precedence = nextOperator(lexer)) != null) {
			right = doShift(lexer, right, precedence.value);
		}
		results.add(right);
	}

	private ASTNode doShift(Lexer lexer, ASTNode left, int value)
			throws ParseException {
		ArrayList<ASTNode> list = new ArrayList<ASTNode>();
		list.add(left);
		list.add(new ASTLeaf(lexer.read()));
		ASTNode right = _parser.parse(lexer);
		Precedence next;
		while ((next = nextOperator(lexer)) != null && rightIsExpr(value, next)) {
			right = doShift(lexer, right, next.value);
		}
		list.add(right);
		return _factory.make(list);
	}

	private Precedence nextOperator(Lexer lexer) throws ParseException {
		Token token = lexer.peek(0);
		if (token.isIdentifier()) {
			return _operators.get(token.getText());
		}
		return null;
	}

	private boolean rightIsExpr(int value, Precedence next) {
		if (next.leftAssociative) {
			return value < next.value;
		}
		return value <= next.value;
	}

	@Override
	protected boolean match(Lexer lexer) throws ParseException {
		return _parser.match(lexer);
	}
}
