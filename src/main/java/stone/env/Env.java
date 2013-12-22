package stone.env;

public interface Env {
	void put(String name, Object value);

	Object get(String name);

	public void putNew(String name, Object value);

	public Env where(String name);

	void setOuter(Env env);
}
