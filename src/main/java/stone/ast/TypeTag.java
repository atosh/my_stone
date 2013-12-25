package stone.ast;

import java.util.List;

public class TypeTag extends ASTList {
	public static final String kUndef = "<Undef>";

	public TypeTag(List<ASTNode> children) {
		super(children);
	}

	public String type() {
		if (numChildren() > 0) {
			return ((ASTLeaf) child(0)).token().getText();
		}
		return kUndef;
	}

	public String toString() {
		return ":" + type();
	}
}
