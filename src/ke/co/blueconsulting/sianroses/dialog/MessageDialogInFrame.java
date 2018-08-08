package ke.co.blueconsulting.sianroses.dialog;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;

public class MessageDialogInFrame {
	
	public static void showMessageDialog(JFrame frame, String message, String title, int ERROR_MESSAGE) {
		JTextPane jTextPane = new JTextPane();
		jTextPane.setBackground(frame.getBackground());
		Document doc = jTextPane.getDocument();
		try {
			doc.insertString(doc.getLength(), message, new SimpleAttributeSet());
			jTextPane.setSize(new Dimension(480, 10));
			jTextPane.setPreferredSize(new Dimension(480, jTextPane.getPreferredSize().height));
			JOptionPane.showMessageDialog(frame, jTextPane, title, ERROR_MESSAGE);
		} catch (BadLocationException ex) {
			//Logger.getLogger(MessageDialogInFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
