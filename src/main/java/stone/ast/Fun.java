package stone.ast;

import java.util.List;

import stone.env.IEnv;
import stone.env.Symbols;
import stone.parser.OptFunction;

public class Fun extends ASTList {
	protected int _size = -1;

	public Fun(List<ASTNode> children) {
		super(children);
	}

	public ParameterList parameters() {
		return (ParameterList) child(0);
	}

	public BlockStatement body() {
		return (BlockStatement) child(1);
	}

	public String toString() {
		return "(fun " + parameters() + " " + body() + ")";
	}

	public Object evaluate(IEnv env) {
		return new OptFunction(parameters(), body(), env, _size);
	}
	
	public void lookup(Symbols symbols) {
		_size = lookup(symbols, parameters(), body());
	}

	public static int lookup(Symbols symbols, ParameterList parameters,
			BlockStatement body) {
		Symbols newSymbols = new Symbols(symbols);
		parameters.lookup(newSymbols);
		body.lookup(newSymbols);
		return newSymbols.size();
	}
}