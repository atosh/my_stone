package stone.ast;

import java.util.List;

public class VarStatement extends ASTList {
	public VarStatement(List<ASTNode> children) {
		super(children);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public TypeTag type() {
		return (TypeTag) child(1);
	}

	public ASTNode initializer() {
		return child(2);
	}

	public String toString() {
		return "(var " + name() + " " + type() + " " + initializer() + ")";
	}
}
