/**
 * 
 */
package stone;

/**
 * @author toshi
 * 
 */
public class StrToken extends Token {

	private String _literal;

	/**
	 * @param lineNumber
	 */
	public StrToken(int lineNumber, String str) {
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
