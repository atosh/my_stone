/**
 * 
 */
package stone;

/**
 * @author toshi
 * 
 */
public class NumberToken extends Token {

	private int _value;

	/**
	 * @param lineNumber
	 * @param parseInt
	 */
	public NumberToken(int lineNumber, int value) {
		super(lineNumber);
		_value = value;
	}

	public boolean isNumber() {
		return true;
	}

	public String getText() {
		return Integer.toString(_value);
	}

	public int getNmber() {
		return _value;
	}
}
