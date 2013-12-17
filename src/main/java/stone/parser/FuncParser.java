package stone.parser;

import static stone.parser.Parser.rule;
import stone.ast.Arguments;
import stone.ast.DefStatement;
import stone.ast.ParameterList;

public class FuncParser extends BasicParser {

	Parser _param = rule().identifier(_reserved);
	Parser _params = rule(ParameterList.class).ast(_param).repeat(
			rule().sep(",").ast(_param));
	Parser _paramList = rule().sep("(").maybe(_params).sep(")");
	Parser _def = rule(DefStatement.class).sep("def").identifier(_reserved)
			.ast(_paramList).ast(_block);
	Parser _args = rule(Arguments.class).ast(_expression).repeat(
			rule().sep(",").ast(_expression));
	Parser _postfix = rule().sep("(").maybe(_args).sep(")");

	public FuncParser() {
		_reserved.add(")");
		_primary.repeat(_postfix);
		// _simple.option(_args);
		_program.insertChoice(_def);
	}
	
}
