package stone.env;

import stone.ast.BlockStatement;
import stone.ast.ParameterList;

public class Function {
	protected ParameterList _parameters;
	protected BlockStatement _body;
	protected Environment _environment;

	public Function(ParameterList parameters, BlockStatement body,
			Environment environment) {
		_parameters = parameters;
		_body = body;
		_environment = environment;
	}

	public ParameterList parameters() {
		return _parameters;
	}

	public BlockStatement body() {
		return _body;
	}

	public Environment makeEnvironment() {
		return new NestedEnvironment(_environment);
	}

	public String toString() {
		return "<fun:" + hashCode() + ">";
	}
}
