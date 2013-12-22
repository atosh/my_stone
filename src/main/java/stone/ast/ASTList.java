package stone.ast;

import java.util.Iterator;
import java.util.List;

import stone.env.Env;
import stone.lexer.StoneException;

public class ASTList extends ASTNode {

	protected List<ASTNode> _children;

	public ASTList(List<ASTNode> children) {
		_children = children;
	}

	@Override
	public ASTNode child(int i) {
		return _children.get(i);
	}

	@Override
	public int numChildren() {
		return _children.size();
	}

	@Override
	public Iterator<ASTNode> children() {
		return _children.iterator();
	}

	@Override
	public String location() {
		for (ASTNode node : _children) {
			String s = node.location();
			if (s != null) {
				return s;
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append('(');
		String sep = "";
		for (ASTNode node : _children) {
			builder.append(sep);
			sep = " ";
			builder.append(node.toString());
		}
		return builder.append(')').toString();
	}

	@Override
	public Object evaluate(Env env) {
		throw new StoneException("cannot eval: " + toString(), this);
	}

}
