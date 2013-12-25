package stone.env;

public interface IEnv {
	void put(String name, Object value);

	Object get(String name);

	public void putNew(String name, Object value);

	public IEnv where(String name);

	void setOuter(IEnv env);
}
