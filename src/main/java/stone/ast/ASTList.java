/**
 * 
 */
package stone.ast;

import java.util.Iterator;
import java.util.List;

/**
 * @author toshi
 *
 */
public class ASTList extends ASTNode {
	protected List<ASTNode> _children;
	public ASTList(List<ASTNode> children) {
		_children = children;
	}
	
	/* (non-Javadoc)
	 * @see stone.ast.ASTNode#child(int)
	 */
	@Override
	public ASTNode child(int i) {
		return _children.get(i);
	}

	/* (non-Javadoc)
	 * @see stone.ast.ASTNode#numChildren()
	 */
	@Override
	public int numChildren() {
		return _children.size();
	}

	/* (non-Javadoc)
	 * @see stone.ast.ASTNode#children()
	 */
	@Override
	public Iterator<ASTNode> children() {
		return _children.iterator();
	}

	/* (non-Javadoc)
	 * @see stone.ast.ASTNode#location()
	 */
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

}
