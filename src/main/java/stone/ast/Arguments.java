package stone.ast;

import java.util.List;

import stone.env.Environment;
import stone.env.Function;
import stone.lexer.StoneException;

public class Arguments extends Postfix {

	public Arguments(List<ASTNode> children) {
		super(children);
	}

	public int size() {
		return numChildren();
	}

	public Object evaluate(Environment callerEnvironment, Object value) {
		if (!(value instanceof Function)) {
			throw new StoneException("bad function", this);
		}
		Function function = (Function) value;
		ParameterList parameters = function.parameters();
		if (size() != parameters.size()) {
			throw new StoneException("bad number of arguments", this);
		}
		Environment newEnvironment = function.makeEnvironment();
		int num = 0;
		for (ASTNode node : this) {
			parameters.evaluate(newEnvironment, num++,
					node.evaluate(callerEnvironment));
		}
		return function.body().evaluate(newEnvironment);
	}
}
