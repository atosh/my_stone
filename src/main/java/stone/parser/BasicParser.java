package stone.parser;

import static stone.parser.Parser.rule;

import java.util.HashSet;

import stone.ast.*;
import stone.lexer.Lexer;
import stone.lexer.ParseException;
import stone.lexer.Token;

public class BasicParser {

	HashSet<String> _reserved = new HashSet<String>();
	Operators _operators = new Operators();
	Parser _expr0 = rule();
	Parser _primary = rule(PrimaryExpr.class).or(
			rule().sep("(").ast(_expr0).sep(")"),
			rule().number(NumberLiteral.class),
			rule().identifier(Name.class, _reserved),
			rule().string(StringLiteral.class));
	Parser _factor = rule().or(
			rule(NegativeExpr.class).sep("-").ast(_primary), _primary);
	Parser _expr = _expr0.expr(BinaryExpr.class,
			_factor, _operators);

	Parser _statement0 = rule();
	Parser _block = rule(BlockStatement.class).sep("{").option(_statement0)
			.repeat(rule().sep(";", Token.kEOL).option(_statement0)).sep("}");
	Parser _simple = rule(PrimaryExpr.class).ast(_expr);
	Parser _statement = _statement0.or(
			rule(IfStatement.class).sep("if").ast(_expr).ast(_block)
					.option(rule().sep("else").ast(_block)),
			rule(WhileStatement.class).sep("while").ast(_expr)
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
