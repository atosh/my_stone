package stone.parser;

import static stone.parser.Parser.rule;
import stone.ast.ClassBody;
import stone.ast.ClassStatement;
import stone.ast.Dot;
import stone.lexer.Token;

public class ClassParser extends ClosureParser {
	Parser _member = rule().or(_def, _simple);
	Parser _classBody = rule(ClassBody.class).sep("{").option(_member)
			.repeat(rule().sep(";", Token.kEOL).option(_member)).sep("}");
	Parser _defClass = rule(ClassStatement.class).sep("class")
			.identifier(_reserved)
			.option(rule().sep("extends").identifier(_reserved))
			.ast(_classBody);

	public ClassParser() {
		_postfix.insertChoice(rule(Dot.class).sep(".").identifier(_reserved));
		_program.insertChoice(_defClass);
	}
}
