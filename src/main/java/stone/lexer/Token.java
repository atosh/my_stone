package stone.lexer;

public abstract class Token {
	public static final Token kEOF = new Token(-1) {
	}; // end of file

	public static final String kEOL = "\\n"; // end of line

	private int _lineNumber;

	public Token(int lineNumber) {
		_lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return _lineNumber;
	}

	public boolean isIdentifier() {
		return false;
	}

	public boolean isNumber() {
		return false;
	}

	public boolean isString() {
		return false;
	}

	public int getNumber() {
		throw new StoneException("not number token.");
	}

	public String getText() {
		return "";
	}
}
