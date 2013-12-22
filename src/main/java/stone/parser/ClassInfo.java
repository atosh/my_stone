package stone.parser;

import stone.ast.ClassBody;
import stone.ast.ClassStatement;
import stone.env.Env;
import stone.lexer.StoneException;

public class ClassInfo {
	protected ClassStatement _definition;
	protected Env _env;
	protected ClassInfo _superClass;

	public ClassInfo(ClassStatement definition, Env env) {
		_definition = definition;
		_env = env;
		Object object = env.get(definition.superClass());
		if (object == null) {
			_superClass = null;
		} else if (object instanceof ClassInfo) {
			_superClass = (ClassInfo) object;
		} else {
			throw new StoneException("unknown super class: "
					+ definition.superClass(), definition);
		}
	}

	public String name() {
		return _definition.name();
	}

	public ClassInfo superClass() {
		return _superClass;
	}

	public ClassBody body() {
		return _definition.body();
	}

	public Env env() {
		return _env;
	}

	@Override
	public String toString() {
		return "<class " + name() + ">";
	}

}
