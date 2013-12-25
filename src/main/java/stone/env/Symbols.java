package stone.env;

import java.util.HashMap;

public class Symbols {
	public static class Location {
		public int _nest;
		public int _index;

		public Location(int nest, int index) {
			_nest = nest;
			_index = index;
		}
	}

	protected Symbols _outer;
	protected HashMap<String, Integer> _table;

	public Symbols() {
		this(null);
	}

	public Symbols(Symbols outer) {
		_outer = outer;
		_table = new HashMap<>();
	}

	public int size() {
		return _table.size();
	}

	public void append(Symbols s) {
		_table.putAll(s._table);
	}

	public Integer find(String key) {
		return _table.get(key);
	}

	public Location get(String key) {
		return get(key, 0);
	}

	public Location get(String key, int nest) {
		Integer index = _table.get(key);
		if (index == null) {
			if (_outer == null) {
				return null;
			}
			return _outer.get(key, nest + 1);
		}
		return new Location(nest, index.intValue());
	}

	public int putNew(String key) {
		Integer i = find(key);
		if (i == null) {
			return add(key);
		}
		return i;
	}

	public Location put(String key) {
		Location location = get(key, 0);
		if (location == null) {
			return new Location(0, add(key));
		}
		return location;
	}

	protected int add(String key) {
		int i = _table.size();
		_table.put(key, i);
		return i;
	}
}
