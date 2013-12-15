package stone.ast;

import java.util.List;

public class DefStatement extends ASTList {

	public DefStatement(List<ASTNode> children) {
		super(children);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public ParameterList paramaters() {
		return (ParameterList) child(1);
	}

	public BlockStatement body() {
		return (BlockStatement) child(2);
	}

	public String toString() {
		return "(def " + name() + " " + paramaters() + " " + body() + ")";
	}
}
