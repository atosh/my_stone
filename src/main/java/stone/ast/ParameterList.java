package stone.ast;

import java.util.List;

import stone.env.Environment;

public class ParameterList extends ASTList {

	public ParameterList(List<ASTNode> children) {
		super(children);
	}

	public String name(int i) {
		return ((ASTLeaf) child(i)).token().getText();
	}

	public int size() {
		return numChildren();
	}

	public void evaluate(Environment environment, int index, Object value) {
		environment.putNew(name(index), value);
	}

}
