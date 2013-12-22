package stone.ast;

import java.util.List;

import stone.env.Env;

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

	public void evaluate(Env env, int index, Object value) {
		env.putNew(name(index), value);
	}

}
