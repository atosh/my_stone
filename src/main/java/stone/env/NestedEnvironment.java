package stone.env;

import java.util.HashMap;

public class NestedEnvironment implements Environment {

	protected HashMap<String, Object> _values = new HashMap<String, Object>();;
	protected Environment _outer;

	public NestedEnvironment() {
		this(null);
	}

	public NestedEnvironment(Environment environment) {
		_outer = environment;
	}

	public void setOuter(Environment environment) {
		_outer = environment;
	}

	public void putNew(String name, Object value) {
		_values.put(name, value);
	}

	public void put(String name, Object value) {
		Environment environment = where(name);
		if (environment == null) {
			environment = this;
		}
		((NestedEnvironment) environment).putNew(name, value);
	}

	public Environment where(String name) {
		if (_values.get(name) != null) {
			return this;
		}
		if (_outer == null) {
			return null;
		}
		return ((NestedEnvironment) _outer).where(name);
	}

	public Object get(String name) {
		Object value = _values.get(name);
		if (value == null && _outer != null) {
			return _outer.get(name);
		}
		return value;
	}

}
