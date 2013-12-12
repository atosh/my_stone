/**
 * 
 */
package stone;

import java.io.IOException;

/**
 * @author toshi
 *
 */
public class ParseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1580580457800772223L;

	public ParseException(Token token) {
		this("", token);
	}
	
	public ParseException(String msg, Token token) {
		super("syntax error around " + location(token) + ". " + msg);
	}

	private static String location(Token token) {
		if (token == Token.kEOF) {
			return "the last line";
		} else {
			return "\"" + token.getText() + "\" at line " + token.getLineNumber();
		}
	}
	
	public ParseException(IOException e) {
		super(e);
	}
	
	public ParseException(String msg) {
		super(msg);
	}
}
