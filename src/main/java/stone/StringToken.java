/**
 * 
 */
package stone;

/**
 * @author toshi
 * 
 */
public class StringToken extends Token {

	private String _literal;

	/**
	 * @param lineNumber
	 */
	public StringToken(int lineNumber, String str) {
		super(lineNumber);
		_literal = str;
	}

	public boolean isString() {
		return true;
	}

	public String getText() {
		return _literal;
	}
}
