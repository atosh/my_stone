package stone.ast;

import java.util.List;

public class Arguments extends Postfix {

	public Arguments(List<ASTNode> children) {
		super(children);
	}

	public int size() {
		return numChildren();
	}

}
