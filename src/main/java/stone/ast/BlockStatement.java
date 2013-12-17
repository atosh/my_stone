package stone.ast;

import java.util.List;

import stone.env.Environment;

public class BlockStatement extends ASTList {

	public BlockStatement(List<ASTNode> children) {
		super(children);
	}

	@Override
	public Object evaluate(Environment environment) {
		Object result = 0;
		for (ASTNode node : this) {
			if (!(node instanceof NullStatement)) {
				result = node.evaluate(environment);
			}
		}
		return result;
	}

}
