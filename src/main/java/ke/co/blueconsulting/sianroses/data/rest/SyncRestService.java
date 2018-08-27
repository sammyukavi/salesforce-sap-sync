package ke.co.blueconsulting.sianroses.data.rest;

import ke.co.blueconsulting.sianroses.model.app.Response;
import retrofit2.Call;
import retrofit2.http.*;

import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.AUTHORIZATION;
import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.DATA_TYPE;

/**
 * A class used by retrofit to make synchronization REST calls
 */
public interface SyncRestService {
	
	@GET("SyncMSSQL")
	Call<Response> fetch(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType);
	
	@POST("SyncMSSQL")
	Call<Response> post(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body Response response);
	
}
