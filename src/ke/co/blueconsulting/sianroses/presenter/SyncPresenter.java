package ke.co.blueconsulting.sianroses.presenter;

import ke.co.blueconsulting.sianroses.BasePresenter;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.fragment.SyncFragment;

import static ke.co.blueconsulting.sianroses.MainApplication.MESSAGE_CODES.ERROR;

public class SyncPresenter extends BasePresenter implements SyncContract.Presenter {
  private SyncContract.View syncFragment;
  
  public SyncPresenter(SyncFragment syncFragment) {
    this.syncFragment = syncFragment;
  }
  
  @Override
  public void testConnection() {
    syncFragment.showMessage("Connection Failed", ERROR);
  }
  
  @Override
  public void sync() {
  
  }
}
