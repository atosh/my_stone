package stone.env;

public interface Environment {
	void put(String name, Object value);

	Object get(String name);

	public void putNew(String name, Object value);

	public Environment where(String name);

	void setOuter(Environment environment);
}
