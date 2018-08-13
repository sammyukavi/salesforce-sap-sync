package ke.co.blueconsulting.sianroses.dialog;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;

public class MessageDialogInFrame {
	
	private static JTextPane getTextPane(String message) {
		JTextPane jTextPane = new JTextPane();
		jTextPane.setBackground(new Color(0, 255, 0, 0));
		jTextPane.setEditable(false);
		Document doc = jTextPane.getDocument();
		try {
			doc.insertString(doc.getLength(), message, new SimpleAttributeSet());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		jTextPane.setSize(new Dimension(480, 10));
		jTextPane.setPreferredSize(new Dimension(480, jTextPane.getPreferredSize().height));
		return jTextPane;
	}
	
	public static void showMessageDialog(JFrame frame, String message, String title, int ERROR_MESSAGE) {
		JOptionPane.showMessageDialog(frame, getTextPane(message), title, ERROR_MESSAGE);
	}
	
	public static void showMessageDialog(JFrame frame, String message, String title, int ERROR_MESSAGE, ImageIcon icon) {
		JOptionPane.showMessageDialog(frame, getTextPane(message), title, ERROR_MESSAGE, icon);
	}
}
