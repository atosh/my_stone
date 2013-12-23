package stone.parser;

import stone.ast.BlockStatement;
import stone.ast.ParameterList;
import stone.env.IEnv;
import stone.env.NestedEnv;

public class Function {
	protected ParameterList _parameters;
	protected BlockStatement _body;
	protected IEnv _env;

	public Function(ParameterList parameters, BlockStatement body,
			IEnv env) {
		_parameters = parameters;
		_body = body;
		_env = env;
	}

	public ParameterList parameters() {
		return _parameters;
	}

	public BlockStatement body() {
		return _body;
	}

	public IEnv makeEnv() {
		return new NestedEnv(_env);
	}

	public String toString() {
		return "<fun:" + hashCode() + ">";
	}
}
