package ke.co.blueconsulting.sianroses.data;

import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.util.ErrorUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.sql.SQLException;

public abstract class BaseDataService<SR, RS> implements DataService<SR> {
	
	protected RS restService;
	protected AuthCredentialsDbService authCredentialsDbService;
	
	protected BaseDataService() {
		restService = RestServiceBuilder.createService(getRestServiceClass());
		try {
			authCredentialsDbService = new AuthCredentialsDbService();
		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
		}
	}
	
	protected abstract Class<RS> getRestServiceClass();
	
	protected void executeSingleTask(final GetCallback<SR> callback, final Call<SR> request) {
		
		request.enqueue(new Callback<SR>() {
			@Override
			public void onResponse(Call<SR> call, Response<SR> response) {
				if (response.isSuccessful()) {
					if (callback != null) {
						callback.onCompleted(response.body());
					}
				} else {
					try {
						Throwable t = new Throwable(ErrorUtil.parseError(response));
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
	
}
