package stone.parser;

import static stone.parser.Parser.rule;
import stone.ast.ArrayLiteral;
import stone.ast.ArrayRef;

public class ArrayParser extends ClassParser {
	Parser _elements = rule(ArrayLiteral.class).ast(_expr).repeat(
			rule().sep(",").ast(_expr));

	public ArrayParser() {
		_reserved.add("]");
		_primary.insertChoice(rule().sep("[").maybe(_elements).sep("]"));
		_postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(_expr).sep("]"));
	}
}
