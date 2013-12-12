/**
 * 
 */
package stone;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author toshi
 * 
 */
public class CodeDialog extends Reader {
	private String buffer;
	private int pos;
	private JTextArea area;
	private JScrollPane pane;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#close()
	 */
//	@Override
	public void close() throws IOException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#read(char[], int, int)
	 */
//	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		if (buffer == null) {
			String in = showDialog();
			if (in == null) {
				return -1;
			}
			print(in);
			buffer = in + "\n";
			pos = 0;
		}
		int size = 0;
		int length = buffer.length();
		while (pos < length && size < len) {
			cbuf[off + size++] = buffer.charAt(pos++);
		}
		if (pos == length) {
			buffer = null;
		}
		return size;
	}

	protected String showDialog() {
		if (area == null) {
			area = new JTextArea(20, 40);
		}
		if (pane == null) {
			pane = new JScrollPane(area);
		}
		int result = JOptionPane.showOptionDialog(null, pane, "Input",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				null, null);
		if (result == JOptionPane.OK_OPTION) {
			return area.getText();
		}
		return null;
	}

	protected void print(String s) {
		System.out.println(s);
	}
	
	public static Reader file() throws FileNotFoundException {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return new BufferedReader(new FileReader(chooser.getSelectedFile()));
		}
		throw new FileNotFoundException("no file specified");
	}
}
