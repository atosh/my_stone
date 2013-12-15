package stone.ast.parser;

import java.util.HashMap;

public class Operators extends HashMap<String, Precedence> {
	private static final long serialVersionUID = -2325620233143129067L;
	public static final boolean kLeft = true;
	public static final boolean kRight = false;

	public void add(String name, int value, boolean leftAssociative) {
		put(name, new Precedence(value, leftAssociative));
	}
}
