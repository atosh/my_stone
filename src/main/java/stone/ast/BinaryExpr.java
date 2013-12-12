/**
 * 
 */
package stone.ast;

import java.util.List;

/**
 * @author toshi
 *
 */
public class BinaryExpr extends ASTList {
	/**
	 * 
	 */
	public BinaryExpr(List<ASTNode> children) {
		super(children);
	}
	
	public ASTNode left() {
		return child(0);
	}
	
	public String operator() {
		return ((ASTLeaf)child(1)).token().getText();
	}
	
	public ASTNode right() {
		return child(2);
	}
}