package stone.env;

import java.util.HashMap;

public class BasicEnvironment implements Environment {

	private HashMap<String, Object> _values = new HashMap<String, Object>();

	public void put(String name, Object value) {
		_values.put(name, value);
	}

	public Object get(String name) {
		return _values.get(name);
	}

}
