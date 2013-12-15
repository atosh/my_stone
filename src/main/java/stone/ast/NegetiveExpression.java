package stone.ast;

import java.util.List;

public class NegetiveExpression extends ASTList {

	public NegetiveExpression(List<ASTNode> children) {
		super(children);
	}

	public ASTNode operand() {
		return child(0);
	}

	public String toString() {
		return "-" + operand();
	}
}
