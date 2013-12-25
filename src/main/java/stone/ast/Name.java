package stone.ast;

import stone.env.ArrayEnv;
import stone.env.IEnv;
import stone.env.Symbols;
import stone.env.Symbols.Location;
import stone.lexer.StoneException;
import stone.lexer.Token;

public class Name extends ASTLeaf {
	protected static final int kUnknown = -1;
	protected int _nest;
	protected int _index = kUnknown;

	public Name(Token token) {
		super(token);
	}

	public String name() {
		return token().getText();
	}

	public void lookup(Symbols symbols) {
		Location location = symbols.get(name());
		if (location == null) {
			throw new StoneException("undefined name: " + name(), this);
		}
		_nest = location._nest;
		_index = location._index;
	}

	public void lookupForAssign(Symbols symbols) {
		Location location = symbols.put(name());
		_nest = location._nest;
		_index = location._index;
	}

	@Override
	public Object evaluate(IEnv env) {
		if (_index == kUnknown) {
			return env.get(name());
		}
		return ((ArrayEnv) env).get(_nest, _index);
	}

	public void evalForAssign(IEnv env, Object value) {
		if (_index == kUnknown) {
			env.put(name(), value);
		} else {
			((ArrayEnv) env).put(_nest, _index, value);
		}
	}
}
