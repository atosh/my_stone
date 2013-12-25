/**
 * 
 */
package stone.lexer;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author toshi
 * 
 */
public class Lexer {
	public static final String kRegexPattern = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
			+ "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
	private Pattern _pattern = Pattern.compile(kRegexPattern);
	private ArrayList<Token> _queue = new ArrayList<Token>();
	private boolean _hasMore;
	private LineNumberReader _reader;

	/**
	 * 
	 */
	public Lexer(Reader reader) {
		_hasMore = true;
		_reader = new LineNumberReader(reader);
	}

	public Token read() throws ParseException {
		if (fillQueue(0)) {
			return _queue.remove(0);
		} else {
			return Token.kEOF;
		}
	}

	public Token peek(int i) throws ParseException {
		if (fillQueue(i)) {
			return _queue.get(i);
		} else {
			return Token.kEOF;
		}
	}

	private boolean fillQueue(int i) throws ParseException {
		while (i >= _queue.size()) {
			if (_hasMore) {
				readLine();
			} else {
				return false;
			}
		}
		return true;
	}

	private void readLine() throws ParseException {
		String line;
		try {
			line = _reader.readLine();
		} catch (IOException e) {
			throw new ParseException(e);
		}
		if (line == null) {
			_hasMore = false;
			return;
		}
		int lineNo = _reader.getLineNumber();
		Matcher matcher = _pattern.matcher(line);
		matcher.useTransparentBounds(true).useAnchoringBounds(false);
		int pos = 0;
		int endPos = line.length();
		while (pos < endPos) {
			matcher.region(pos, endPos);
			if (matcher.lookingAt()) {
				addToken(lineNo, matcher);
				pos = matcher.end();
			} else {
				throw new ParseException("bad token at line" + lineNo);
			}
		}
		_queue.add(new IdToken(lineNo, Token.kEOL));
	}

	/**
	 * @param lineNo
	 * @param matcher
	 */
	private void addToken(int lineNo, Matcher matcher) {
		String m = matcher.group(1);
		if (m != null) { // if not a space
			if (matcher.group(2) == null) { // if not a comment
				Token token;
				if (matcher.group(3) != null) {
					token = new NumberToken(lineNo, Integer.parseInt(m));
				} else if (matcher.group(4) != null) {
					token = new StringToken(lineNo, toStringLiteral(m));
				} else {
					token = new IdToken(lineNo, m);
				}
				_queue.add(token);
			}
		}
	}

	/**
	 * @param s
	 * @return
	 */
	private String toStringLiteral(String s) {
		StringBuilder sb = new StringBuilder();
		int len = s.length() - 1;
		for (int i = 1; i < len; i++) {
			char c = s.charAt(i);
			if (c == '\\' && i + 1 < len) {
				int c2 = s.charAt(i + 1);
				if (c2 == '"' || c2 == '\\') {
					c = s.charAt(++i);
				} else if (c2 == 'n') {
					++i;
					c = '\n';
				}
			}
			sb.append(c);
		}
		return sb.toString();
	}
}
