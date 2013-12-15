package stone.ast;

import java.util.List;

import stone.ast.ASTList;

public class PrimaryExpression extends ASTList {

	public PrimaryExpression(List<ASTNode> children) {
		super(children);
	}

	public static ASTNode create(List<ASTNode> children) {
		return children.size() == 1 ? children.get(0) : new PrimaryExpression(
				children);
	}
}
