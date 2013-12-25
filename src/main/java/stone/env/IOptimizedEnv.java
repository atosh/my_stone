package stone.env;

public interface IOptimizedEnv extends IEnv {
	Symbols symbols();

	void put(int nest, int index, Object value);

	Object get(int nest, int index);

	public void putNew(String name, Object value);

	public IEnv where(String name);

	void setOuter(IEnv env);
}
