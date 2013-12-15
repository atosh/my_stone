package stone;

import static stone.ast.parser.Parser.rule;

import java.util.HashSet;

import stone.ast.*;
import stone.ast.parser.Parser;
import stone.ast.parser.Operators;

public class BasicParser {

	HashSet<String> _reserved = new HashSet<String>();
	Operators _operators = new Operators();
	Parser _expression0 = rule();
	Parser _primary = rule(PrimaryExpression.class).or(
			rule().sep("(").ast(_expression0).sep(")"),
			rule().number(NumberLiteral.class),
			rule().identifier(Name.class, _reserved),
			rule().string(StringLiteral.class));
	Parser _factor = rule().or(
			rule(NegetiveExpression.class).sep("-").ast(_primary), _primary);
	Parser _expression = _expression0.expression(BinaryExpression.class,
			_factor, _operators);

	Parser _statement0 = rule();
	Parser _block = rule(BlockStatement.class).sep("{").option(_statement0)
			.repeat(rule().sep(";", Token.kEOL).option(_statement0)).sep("}");
	Parser _simple = rule(PrimaryExpression.class).ast(_expression);
	Parser _statement = _statement0.or(
			rule(IfStatement.class).sep("if").ast(_expression).ast(_block)
					.option(rule().sep("else").ast(_block)),
			rule(WhileStatement.class).sep("while").ast(_expression)
					.ast(_block), _simple);

	Parser _program = rule().or(_statement, rule(NullStatement.class))
			.sep(";", Token.kEOL);

	public BasicParser() {
		_reserved.add(";");
		_reserved.add("}");
		_reserved.add(Token.kEOL);

		_operators.add("=", 1, Operators.kRight);
		_operators.add("==", 2, Operators.kLeft);
		_operators.add(">", 2, Operators.kLeft);
		_operators.add("<", 2, Operators.kLeft);
		_operators.add("+", 3, Operators.kLeft);
		_operators.add("-", 3, Operators.kLeft);
		_operators.add("*", 4, Operators.kLeft);
		_operators.add("/", 4, Operators.kLeft);
		_operators.add("%", 4, Operators.kLeft);
	}

	public ASTNode parse(Lexer lexer) throws ParseException {
		return _program.parse(lexer);
	}
}
