package ke.co.blueconsulting.sianroses.data;

import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.util.AppLogger;
import ke.co.blueconsulting.sianroses.util.ErrorHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.sql.SQLException;

/**
 * A blue print used to create a data service to make REST calls
 *
 * @param <M> Model used to make REST request
 * @param <R> Rest Data Service. An interface that contains the calls to be made
 *            via REST
 */
public abstract class BaseDataService<M, R> implements DataService<M> {
	
	protected R restService;
	protected AuthCredentialsDbService authCredentialsDbService;
	
	protected BaseDataService() {
		restService = RestServiceBuilder.createService(getRestServiceClass());
		try {
			authCredentialsDbService = new AuthCredentialsDbService();
		} catch (ClassNotFoundException | SQLException e) {
			AppLogger.logError("An error occured in the BaseDataService Constructor. " + e.getMessage());
		}
	}
	
	/**
	 * Get an instance of the Class used to make REST calls
	 *
	 * @return Class Instance
	 */
	protected abstract Class<R> getRestServiceClass();
	
	/**
	 * Method used to perform a REST task and return the results
	 *
	 * @param callback Method called one the transaction is done.
	 * @param request  The REST call being performed
	 */
	protected void executeSingleTask(final GetCallback<M> callback, final Call<M> request) {
		
		request.enqueue(new Callback<M>() {
			@Override
			public void onResponse(Call<M> call, Response<M> response) {
				
				if (response.isSuccessful()) {
					if (callback != null) {
						callback.onCompleted(response.body());
					}
				} else {
					Throwable t = new Throwable(ErrorHandler.parseError(response));
					if (callback != null) {
						callback.onError(t);
					}
				}
				
				if (callback != null) {
					callback.always();
				}
			}
			
			@Override
			public void onFailure(Call<M> call, Throwable t) {
				if (callback != null) {
					callback.onError(t);
				}
				
				if (callback != null) {
					callback.always();
				}
			}
		});
	}
}
