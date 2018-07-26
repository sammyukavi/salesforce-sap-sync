package ke.co.blueconsulting.sianroses.dialogs;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;

/**
 * @author sukavi
 */
public class MessageDialogInFrame {
	
	public static void showMessageDialog(JFrame frame, String message, String title, int ERROR_MESSAGE) {
		JTextPane jtp = new JTextPane();
		jtp.setBackground(frame.getBackground());
		Document doc = jtp.getDocument();
		try {
			doc.insertString(doc.getLength(), message, new SimpleAttributeSet());
			jtp.setSize(new Dimension(480, 10));
			jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
			JOptionPane.showMessageDialog(frame, jtp, title, ERROR_MESSAGE);
		} catch (BadLocationException ex) {
			//Logger.getLogger(MessageDialogInFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
