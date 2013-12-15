/**
 * 
 */
package stone;

/**
 * @author toshi
 * 
 */
public class IdToken extends Token {

	private String _text;

	/**
	 * @param lineNumber
	 * @param id
	 */
	public IdToken(int lineNumber, String id) {
		super(lineNumber);
		_text = id;
	}

	public boolean isIdentifier() {
		return true;
	}

	public String getText() {
		return _text;
	}
}
