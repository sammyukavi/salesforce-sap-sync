package ke.co.blueconsulting.sianroses.data;

import ke.co.blueconsulting.sianroses.model.app.ServerResponse;
import ke.co.blueconsulting.sianroses.util.ErrorUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseDataService<SR, RS, DS> implements DataService<SR> {
  
  protected RS restService;
  protected DS dbService;
  
  protected BaseDataService() {
    restService = RestServiceBuilder.createService(getRestServiceClass());
    dbService = getDbServiceInstanceClass();
  }
  
  protected abstract Class<RS> getRestServiceClass();
  
  protected abstract DS getDbServiceInstanceClass();
  
  protected void executeSingleTask(final GetCallback<SR> callback, final SR dbResults) {
    callback.onCompleted(dbResults);
  }
  
  protected void executeSingleTask(final GetCallback<SR> callback, final Call<SR> restQuery) {
    
    restQuery.enqueue(new Callback<SR>() {
      
      @Override
      public void onResponse(Call<SR> call, Response<SR> response) {
        if (response.isSuccessful()) {
          if (callback != null) {
            callback.onCompleted(response.body());
          }
        } else {
          ServerResponse error = ErrorUtil.parseError(response);
          try {
            Throwable t = new Throwable(error.getError() + "\n" + error.getErrorDescription());
            if (callback != null) {
              callback.onError(t);
            }
          } catch (Exception t) {
            if (callback != null) {
              callback.onError(t);
            }
          }
        }
      }
      
      @Override
      public void onFailure(Call<SR> call, Throwable t) {
        if (callback != null) {
          callback.onError(t);
        }
      }
    });
    
  }
  
  protected void executeMultipleTasks(final GetCallback<SR> callback, final Call<SR> restQuery,
                                      final SR dbResults) {
    restQuery.enqueue(new Callback<SR>() {
      
      @Override
      public void onResponse(Call<SR> call, Response<SR> response) {
        if (response.isSuccessful()) {
          callback.onCompleted(response.body());
        } else {
          ServerResponse error = ErrorUtil.parseError(response);
          Throwable t = new Throwable(error.getError() + "\n" + error.getErrorDescription());
          callback.onError(t);
        }
      }
      
      @Override
      public void onFailure(Call<SR> call, Throwable t) {
        callback.onCompleted(dbResults);
      }
    });
  }
  
}
