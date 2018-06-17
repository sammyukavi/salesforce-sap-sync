package ke.co.blueconsulting.sianroses;

import ke.co.blueconsulting.sianroses.fragment.SyncFragment;

import java.awt.*;

public class MainApplication {
  
  public static class CONSTANTS {
    public static final String APP_DIR_NAME = ".sianroses";
  }
  
  public static class MESSAGE_CODES {
    public static final int ERROR = 1;
  }
  
  public static void main(String[] args) {
    
    EventQueue.invokeLater(() -> {
      try {
        new SyncFragment();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
