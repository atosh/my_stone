package stone.env;

import stone.lexer.StoneException;

public class ArrayEnv implements IOptimizedEnv {
	protected Object[] _values;
	protected IEnv _outer;

	public ArrayEnv(int size, IEnv outer) {
		_values = new Object[size];
		_outer = outer;
	}

	@Override
	public Symbols symbols() {
		throw new StoneException("no symbols.");
	}

	@Override
	public Object get(int nest, int index) {
		if (nest == 0) {
			return _values[index];
		} else if (_outer == null) {
			return null;
		}
		return ((ArrayEnv) _outer).get(nest - 1, index);
	}

	@Override
	public void put(int nest, int index, Object value) {
		if (nest == 0) {
			_values[index] = value;
		} else if (_outer == null) {
			throw new StoneException("no outer environment.");
		}
		((ArrayEnv) _outer).put(nest - 1, index, value);
	}

	@Override
	public Object get(String name) {
		error(name);
		return null;
	}

	@Override
	public void put(String name, Object value) {
		error(name);
	}

	@Override
	public void putNew(String name, Object value) {
		error(name);
	}

	@Override
	public IEnv where(String name) {
		error(name);
		return null;
	}

	@Override
	public void setOuter(IEnv outer) {
		if (!(outer instanceof ArrayEnv)) {
			throw new StoneException("use ArrayEnv instance.");
		}
		_outer = (ArrayEnv) outer;
	}

	public void error(String name) {
		throw new StoneException("cannot access by name: " + name);
	}
}
