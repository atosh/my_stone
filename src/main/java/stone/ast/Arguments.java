package stone.ast;

import java.util.List;

import stone.env.Env;
import stone.lexer.StoneException;
import stone.parser.Function;

public class Arguments extends Postfix {

	public Arguments(List<ASTNode> children) {
		super(children);
	}

	public int size() {
		return numChildren();
	}

	public Object evaluate(Env callerEnv, Object value) {
		if (!(value instanceof Function)) {
			throw new StoneException("bad function", this);
		}
		Function function = (Function) value;
		ParameterList parameters = function.parameters();
		if (size() != parameters.size()) {
			throw new StoneException("bad number of arguments", this);
		}
		Env newEnv = function.makeEnv();
		int num = 0;
		for (ASTNode node : this) {
			parameters.evaluate(newEnv, num++,
					node.evaluate(callerEnv));
		}
		return function.body().evaluate(newEnv);
	}
}
