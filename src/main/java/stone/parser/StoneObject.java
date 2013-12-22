package stone.parser;

import stone.env.Env;

public class StoneObject {

	public static class AccessException extends Exception {}
	private Env _env;

	public StoneObject(Env env) {
		_env = env;
	}
	
	@Override
	public String toString() {
		return "<object:" + hashCode() + ">";
	}

	public Object read(String member) throws AccessException {
		return getEnv(member).get(member);
	}

	public void write(String member, Object value) throws AccessException {
		getEnv(member).putNew(member, value);
	}
	
	protected Env getEnv(String member) throws AccessException {
		Env env = _env.where(member);
		if (env != null && env == _env) {
			return env;
		}
		throw new AccessException();
	}

}
