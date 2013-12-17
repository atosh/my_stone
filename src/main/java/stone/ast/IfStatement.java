package stone.ast;

import java.util.List;

import stone.env.Environment;

public class IfStatement extends ASTList {

	public IfStatement(List<ASTNode> children) {
		super(children);
	}

	public ASTNode condition() {
		return child(0);
	}

	public ASTNode thenBlock() {
		return child(1);
	}

	public ASTNode elseBlock() {
		return numChildren() > 2 ? child(2) : null;
	}

	public String toString() {
		return "if( " + condition() + " " + thenBlock() + " else "
				+ elseBlock() + ")";
	}

	@Override
	public Object evaluate(Environment environment) {
		Object cond = condition().evaluate(environment);
		if (cond instanceof Boolean && ((Boolean) cond).booleanValue()) {
			return thenBlock().evaluate(environment);
		}
		ASTNode block = elseBlock();
		if (block == null) {
			return 0;
		}
		return block.evaluate(environment);
	}

}
