package stone.parser;

import static stone.parser.Parser.rule;

import stone.ast.Fun;

public class ClosureParser extends FuncParser {
	public ClosureParser() {
		_primary.insertChoice(rule(Fun.class).sep("fun").ast(_paramList).ast(_block));
	}
}
