package stone.ast;

import java.util.List;

import stone.env.IEnv;
import stone.env.IOptimizedEnv;
import stone.env.Symbols;
import stone.parser.OptFunction;

public class DefStatement extends ASTList {
	protected int index;
	protected int size;

	public DefStatement(List<ASTNode> children) {
		super(children);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public ParameterList parameters() {
		return (ParameterList) child(1);
	}

	public BlockStatement body() {
		return (BlockStatement) child(2);
	}

	public String toString() {
		return "(def " + name() + " " + parameters() + " " + body() + ")";
	}

	public Object evaluate(IEnv env) {
		((IOptimizedEnv) env).put(0, index, new OptFunction(parameters(), body(),
				env, size));
		return name();
	}

	public void lookup(Symbols symbols) {
		index = symbols.putNew(name());
		size = Fun.lookup(symbols, parameters(), body());
	}
}
