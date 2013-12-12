/**
 * 
 */
package stone;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import stone.ast.ASTLeaf;
import stone.ast.ASTList;
import stone.ast.ASTNode;

/**
 * @author toshi
 *
 */
public class Parser {
	public static final String kFactoryName = "create";

	public static abstract class Factory {
		protected abstract ASTNode make0(Object arg) throws Exception;
		/**
		 * @param arg
		 * @return
		 */
		public ASTNode make(Object arg) {
			try {
				return make0(arg);
			} catch (IllegalArgumentException e) {
				throw e;
			} catch (Exception e) {
				throw new RuntimeException(e); // this compiler is broken.
			}
		}

		/**
		 * @param type
		 * @param class1
		 * @return
		 */
		public static Factory get(Class<? extends ASTNode> type,
				Class<?> argType) {
			if (type == null) {
				return null;
			}
			try {
				final Method method = type.getMethod(kFactoryName, new Class<?>[]{argType});
				return new Factory() {
					protected ASTNode make0(Object args) throws Exception {
						return (ASTNode)method.invoke(null, args);
					}
				};
			} catch (NoSuchMethodException e) {
			} catch (SecurityException e) {
			}
			try {
				final Constructor<? extends ASTNode> constructor = type.getConstructor(argType);
				return new Factory() {
					protected ASTNode make0(Object args) throws Exception {
						return constructor.newInstance(args);
					}
				};
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(e);
			} catch (SecurityException e) {
			}
			return null;
		}

	}

	protected static abstract class Element {
		protected abstract void parse(Lexer lexer, List<ASTNode> res)
			throws ParseException;
		protected abstract boolean match(Lexer lexer) throws ParseException;
	}
	
	protected static class Tree extends Element {

		private Parser _parser;
		
		/**
		 * 
		 */
		protected Tree(Parser parser) {
			_parser = parser;
		}

		/* (non-Javadoc)
		 * @see stone.Parser.Element#parse(stone.Lexer, java.util.List)
		 */
		@Override
		protected void parse(Lexer lexer, List<ASTNode> res)
				throws ParseException {
			res.add(_parser.parse(lexer));
		}

		/* (non-Javadoc)
		 * @see stone.Parser.Element#match(stone.Lexer)
		 */
		@Override
		protected boolean match(Lexer lexer) throws ParseException {
			return _parser.match(lexer);
		}
		
	}
	
	protected static class OrTree extends Element {

		private Parser[] _parsers;

		/**
		 * 
		 */
		protected OrTree(Parser[] parsers) {
			_parsers = parsers;
		}
		/* (non-Javadoc)
		 * @see stone.Parser.Element#parse(stone.Lexer, java.util.List)
		 */
		@Override
		protected void parse(Lexer lexer, List<ASTNode> res)
				throws ParseException {
			Parser parser = choose(lexer);
			if (parser == null) {
				throw new ParseException(lexer.peek(0));
			} else {
				res.add(parser.parse(lexer));
			}
		}

		/* (non-Javadoc)
		 * @see stone.Parser.Element#match(stone.Lexer)
		 */
		@Override
		protected boolean match(Lexer lexer) throws ParseException {
			return choose(lexer) != null;
		}
		
		/**
		 * @param lexer
		 * @return
		 */
		protected Parser choose(Lexer lexer) throws ParseException {
			for (Parser parser : _parsers) {
				if (parser.match(lexer)) {
					return parser;
				}
			}
			return null;
		}
		
		/**
		 * 
		 * @param parser
		 */
		protected void insert(Parser parser) {
			Parser[] newParsers = new Parser[_parsers.length + 1];
			newParsers[0] = parser;
			System.arraycopy(_parsers, 0, newParsers, 1, _parsers.length);
			_parsers = newParsers;
		}
	}
	
	protected static class Repeat extends Element {

		private Parser _parser;
		private boolean _onlyOnce;

		/**
		 * 
		 */
		protected Repeat(Parser parser, boolean once) {
			_parser = parser;
			_onlyOnce = once;
		}
		/* (non-Javadoc)
		 * @see stone.Parser.Element#parse(stone.Lexer, java.util.List)
		 */
		@Override
		protected void parse(Lexer lexer, List<ASTNode> res)
				throws ParseException {
			while (_parser.match(lexer)) {
				ASTNode node = _parser.parse(lexer);
				if (node.getClass() != ASTList.class || node.numChildren() > 0) {
					res.add(node);
				}
				if (_onlyOnce) {
					break;
				}
			}
			
		}

		/* (non-Javadoc)
		 * @see stone.Parser.Element#match(stone.Lexer)
		 */
		@Override
		protected boolean match(Lexer lexer) throws ParseException {
			return _parser.match(lexer);
		}
		
	}
	
	protected static abstract class AToken extends Element {
		private Factory _factory;

		/**
		 * 
		 */
		protected AToken(Class<? extends ASTLeaf> type) {
			if (type == null) {
				type = ASTLeaf.class;
			}
			_factory = Factory.get(type, Token.class);
		}
		
		protected void parse(Lexer lexer, List<ASTNode> res) throws ParseException {
			Token token = lexer.read();
			if (test(token)) {
				ASTNode leaf = _factory.make(token);
				res.add(leaf);
			} else {
				throw new ParseException(token);
			}
		}
		
		protected boolean match(Lexer lexer) throws ParseException {
			return test(lexer.peek(0));
		}

		protected abstract boolean test(Token token);
	}
	
	/**
	 * 
	 */
	public Parser() {
	}

	/**
	 * @param lexer
	 * @return
	 */
	public boolean match(Lexer lexer) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param lexer
	 * @return
	 */
	public ASTNode parse(Lexer lexer) {
		// TODO Auto-generated method stub
		return null;
	}

}
