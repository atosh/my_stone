package stone.ast;

import java.util.List;

import stone.env.IEnv;
import stone.env.IOptimizedEnv;
import stone.env.Symbols;

public class ParameterList extends ASTList {
	protected int[] _offsets;
	
	public ParameterList(List<ASTNode> children) {
		super(children);
	}

	public String name(int i) {
		return ((ASTLeaf) child(i)).token().getText();
	}

	public int size() {
		return numChildren();
	}
	
	public void lookup(Symbols symbols) {
		int s = size();
		_offsets = new int[s];
		for (int i = 0; i < s; i++) {
			_offsets[i] = symbols.putNew(name(i));
		}
	}

	public void evaluate(IEnv env, int index, Object value) {
		((IOptimizedEnv) env).put(0, _offsets[index], value);
	}
}
