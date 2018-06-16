package ke.co.blueconsulting.sianroses;

public abstract class BaseFragment<T> implements BaseView<T> {
  
  protected T mPresenter;
  
  @Override
  public void setPresenter(T presenter) {
    mPresenter = presenter;
  }
  
}
