/**
 * 
 */
package stone;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author toshi
 *
 */
public class LexerTest {

	@Test
	public void test() throws ParseException {
		Lexer lexer = new Lexer(new CodeDialog());
		for (Token token; (token = lexer.read()) != Token.kEOF; ) {
			System.out.println("=> " + token.getText());
		}
	}

}
