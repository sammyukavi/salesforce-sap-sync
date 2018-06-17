package ke.co.blueconsulting.sianroses.contract;

import ke.co.blueconsulting.sianroses.BaseView;

public class SyncContract {
  
  public interface View extends BaseView<Presenter> {
    
    void showMessage(String message, int error_code);
    
    void showStatus(boolean isBusy);
  }
  
  public interface Presenter {
    
    void testConnection();
    
    void sync();
    
  }
}
