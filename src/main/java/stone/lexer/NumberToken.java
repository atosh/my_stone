package stone.lexer;

public class NumberToken extends Token {

	private int _value;

	public NumberToken(int lineNumber, int value) {
		super(lineNumber);
		_value = value;
	}

	@Override
	public boolean isNumber() {
		return true;
	}

	@Override
	public String getText() {
		return Integer.toString(_value);
	}

	@Override
	public int getNumber() {
		return _value;
	}
}
