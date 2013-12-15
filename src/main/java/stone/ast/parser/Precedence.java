package stone.ast.parser;

public class Precedence {
	int value;
	boolean leftAssociative;

	public Precedence(int value, boolean leftAssociative) {
		this.value = value;
		this.leftAssociative = leftAssociative;
	}
}

