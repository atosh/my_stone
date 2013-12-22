package stone.env;

import java.util.HashMap;

import stone.lexer.StoneException;

public class BasicEnv implements Env {

	private HashMap<String, Object> _values = new HashMap<String, Object>();

	@Override
	public void put(String name, Object value) {
		_values.put(name, value);
	}

	@Override
	public Object get(String name) {
		return _values.get(name);
	}

	@Override
	public void putNew(String name, Object value) {
		throw new StoneException("invalid method.");
	}

	@Override
	public Env where(String name) {
		throw new StoneException("invalid method.");
	}

	@Override
	public void setOuter(Env env) {
		throw new StoneException("invalid method.");
	}
}
