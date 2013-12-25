package stone.parser;

import stone.ast.BlockStatement;
import stone.ast.ParameterList;
import stone.env.ArrayEnv;
import stone.env.IEnv;

public class OptFunction extends Function {
	protected int _size;

	public OptFunction(ParameterList parameters, BlockStatement body, IEnv env,
			int memorySize) {
		super(parameters, body, env);
		_size = memorySize;
	}

	@Override
	public IEnv makeEnv() {
		return new ArrayEnv(_size, _env);
	}
}
