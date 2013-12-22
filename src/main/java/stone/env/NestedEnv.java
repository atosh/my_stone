package stone.env;

import java.util.HashMap;

public class NestedEnv implements Env {

	protected HashMap<String, Object> _values = new HashMap<String, Object>();;
	protected Env _outer;

	public NestedEnv() {
		this(null);
	}

	public NestedEnv(Env env) {
		_outer = env;
	}

	public void setOuter(Env env) {
		_outer = env;
	}

	public void putNew(String name, Object value) {
		_values.put(name, value);
	}

	public void put(String name, Object value) {
		Env env = where(name);
		if (env == null) {
			env = this;
		}
		((NestedEnv) env).putNew(name, value);
	}

	public Env where(String name) {
		if (_values.get(name) != null) {
			return this;
		}
		if (_outer == null) {
			return null;
		}
		return ((NestedEnv) _outer).where(name);
	}

	public Object get(String name) {
		Object value = _values.get(name);
		if (value == null && _outer != null) {
			return _outer.get(name);
		}
		return value;
	}

}
