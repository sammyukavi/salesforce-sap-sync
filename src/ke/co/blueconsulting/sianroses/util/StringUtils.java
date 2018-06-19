package ke.co.blueconsulting.sianroses.util;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.validator.routines.UrlValidator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.beans.PropertyChangeEvent;
import java.util.Objects;

public class StringUtils {
  private static final String NULL_AS_STRING = "null";
  private static final String SPACE_CHAR = " ";
  private final static String NON_THIN = "[^iIl1\\.,']";
  
  public static boolean isValidURL(String url) {
    String[] schemes = {"http", "https"};
    UrlValidator urlValidator = new UrlValidator(schemes);
    return urlValidator.isValid(url);
  }
  
  public static boolean isValidIP(String ip) {
    InetAddressValidator addressValidator = new InetAddressValidator();
    return addressValidator.isValid(ip);
  }
  
  public static boolean notNull(String string) {
    return null != string && !NULL_AS_STRING.equals(string.trim());
  }
  
  public static boolean isBlank(String string) {
    return null == string || SPACE_CHAR.equals(string);
  }
  
  public static boolean notEmpty(String string) {
    return !isNullOrEmpty(string);
  }
  
  public static boolean isNullOrEmpty(String string) {
    return string == null || string.isEmpty();
  }
  
  public static void addChangeListener(JTextComponent text, ChangeListener changeListener) {
    Objects.requireNonNull(text);
    Objects.requireNonNull(changeListener);
    DocumentListener dl = new DocumentListener() {
      private int lastChange = 0, lastNotifiedChange = 0;
      
      @Override
      public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
      }
      
      @Override
      public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
      }
      
      @Override
      public void changedUpdate(DocumentEvent e) {
        lastChange++;
        SwingUtilities.invokeLater(() -> {
          if (lastNotifiedChange != lastChange) {
            lastNotifiedChange = lastChange;
            changeListener.stateChanged(new ChangeEvent(text));
          }
        });
      }
    };
    text.addPropertyChangeListener("document", (PropertyChangeEvent e) -> {
      Document d1 = (Document) e.getOldValue();
      Document d2 = (Document) e.getNewValue();
      if (d1 != null) d1.removeDocumentListener(dl);
      if (d2 != null) d2.addDocumentListener(dl);
      dl.changedUpdate(null);
    });
    Document d = text.getDocument();
    if (d != null) d.addDocumentListener(dl);
  }
  
}
