/**
 * 
 */
package stone;

/**
 * @author toshi
 * 
 */
public class NumToken extends Token {

	private int _value;

	/**
	 * @param lineNumber
	 * @param parseInt
	 */
	public NumToken(int lineNumber, int value) {
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
