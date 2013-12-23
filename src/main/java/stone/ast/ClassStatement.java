package stone.ast;

import java.util.List;

import stone.env.IEnv;
import stone.parser.ClassInfo;

public class ClassStatement extends ASTList {

	public ClassStatement(List<ASTNode> children) {
		super(children);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public String superClass() {
		if (numChildren() < 3) {
			return null;
		}
		return ((ASTLeaf) child(1)).token().getText();
	}

	public ClassBody body() {
		return (ClassBody) child(numChildren() - 1);
	}

	public String toString() {
		String parent = superClass();
		if (parent == null) {
			parent = "*";
		}
		return "(Class " + name() + " " + parent + " " + body() + ")";
	}

	public Object evaluate(IEnv env) {
		ClassInfo info = new ClassInfo(this, env);
		env.put(name(), info);
		return name();
	}

}
