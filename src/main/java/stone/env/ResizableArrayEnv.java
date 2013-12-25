package stone.env;

import java.util.Arrays;

public class ResizableArrayEnv extends ArrayEnv {
	protected Symbols _names = new Symbols();

	public ResizableArrayEnv() {
		super(10, null);
	}

	@Override
	public Symbols symbols() {
		return _names;
	}
	
	@Override
	public Object get(String name) {
		Integer i = _names.find(name);
		if (i == null) {
			if (_outer == null) {
				return null;
			}
			return _outer.get(name);
		}
		return _values[i];
	}
	
	@Override
	public void put(String name, Object value) {
		IEnv env = where(name);
		if (env == null) {
			env = this;
		}
		env.putNew(name, value);
	}
	
	@Override
	public void putNew(String name, Object value) {
		assign(_names.putNew(name), value);
	}
	
	@Override
	public IEnv where(String name) {
		if (_names.find(name) != null) {
			return this;
		} else if (_outer == null) {
			return null;
		}
		return _outer.where(name);
	}
	
	@Override
	public void put(int nest, int index, Object value) {
		if (nest == 0) {
			assign(index, value);
		}
		super.put(nest, index, value);
	}
	
	protected void assign(int index, Object value) {
		if (index >= _values.length) {
			int newLength = _values.length * 2;
			if (index >= newLength) {
				newLength = index + 1;
			}
			_values = Arrays.copyOf(_values, newLength);
		}
		_values[index] = value;
	}
}
