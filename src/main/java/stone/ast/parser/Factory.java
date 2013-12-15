package stone.ast.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import stone.ast.ASTList;
import stone.ast.ASTNode;

public abstract class Factory {
	protected abstract ASTNode make0(Object arg) throws Exception;

	public ASTNode make(Object arg) {
		try {
			return make0(arg);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e); // this compiler is broken.
		}
	}

	public static Factory get(Class<? extends ASTNode> type, Class<?> argType) {
		if (type == null) {
			return null;
		}
		try {
			final Method method = type.getMethod(Parser.kFactoryName,
					new Class<?>[] { argType });
			return new Factory() {
				protected ASTNode make0(Object args) throws Exception {
					return (ASTNode) method.invoke(null, args);
				}
			};
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}
		try {
			final Constructor<? extends ASTNode> constructor = type
					.getConstructor(argType);
			return new Factory() {
				protected ASTNode make0(Object args) throws Exception {
					return constructor.newInstance(args);
				}
			};
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
		}
		return null;
	}

	public static Factory getForASTList(Class<? extends ASTNode> type) {
		Factory factory = get(type, List.class);
		if (factory == null) {
			factory = new Factory() {
				protected ASTNode make0(Object args) throws Exception {
					@SuppressWarnings("unchecked")
					List<ASTNode> results = (List<ASTNode>) args;
					if (results.size() == 1) {
						return results.get(0);
					} else {
						return new ASTList(results);
					}
				}
			};
		}
		return factory;
	}

}
