package ke.co.blueconsulting.sianroses.dialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class YesNoDialog extends JDialog {
  
  private static final long serialVersionUID = -6317245189234897873L;
  
  public YesNoDialog() {
    setBounds(100, 100, 450, 300);
    getContentPane().setLayout(new BorderLayout());
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new FlowLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    {
      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
      getContentPane().add(buttonPane, BorderLayout.SOUTH);
      {
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
      }
      {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
      }
    }
  }
  
}
