package stone.lexer;

public class StringToken extends Token {
	private String _literal;

	public StringToken(int lineNumber, String str) {
		super(lineNumber);
		_literal = str;
	}

	@Override
	public boolean isString() {
		return true;
	}

	@Override
	public String getText() {
		return _literal;
	}
}
