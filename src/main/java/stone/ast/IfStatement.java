package stone.ast;

import java.util.List;

import stone.env.IEnv;

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
	public Object evaluate(IEnv env) {
		Object cond = condition().evaluate(env);
		if (cond instanceof Boolean && ((Boolean) cond).booleanValue()) {
			return thenBlock().evaluate(env);
		}
		ASTNode block = elseBlock();
		if (block == null) {
			return 0;
		}
		return block.evaluate(env);
	}

}
