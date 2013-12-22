package stone.lexer;

public class IdToken extends Token {

	private String _text;

	public IdToken(int lineNumber, String id) {
		super(lineNumber);
		_text = id;
	}

	@Override
	public boolean isIdentifier() {
		return true;
	}

	@Override
	public String getText() {
		return _text;
	}

}
