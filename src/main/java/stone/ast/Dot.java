package stone.ast;

import java.util.List;

import stone.env.IEnv;
import stone.env.NestedEnv;
import stone.lexer.StoneException;
import stone.parser.ClassInfo;
import stone.parser.StoneObject;
import stone.parser.StoneObject.AccessException;

public class Dot extends Postfix {
	public Dot(List<ASTNode> children) {
		super(children);
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public String toString() {
		return "." + name();
	}

	@Override
	public Object evaluate(IEnv env, Object value) {
		String member = name();
		if (value instanceof ClassInfo) {
			if ("new".equals(member)) {
				ClassInfo info = (ClassInfo) value;
				NestedEnv env2 = new NestedEnv(info.env());
				StoneObject object = new StoneObject(env2);
				env2.putNew("this", object);
				initObject(info, env2);
				return object;
			}
		} else if (value instanceof StoneObject) {
			try {
				return ((StoneObject) value).read(member);
			} catch (AccessException e) {
			}
		}
		throw new StoneException("bad member access: " + member, this);
	}

	private void initObject(ClassInfo info, IEnv env) {
		if (info.superClass() != null) {
			initObject(info.superClass(), env);
		}
		info.body().evaluate(env);
	}
}
