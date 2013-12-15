package stone.ast;

import java.util.List;

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

}
