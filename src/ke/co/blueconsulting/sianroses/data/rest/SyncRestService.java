package ke.co.blueconsulting.sianroses.data.rest;

import ke.co.blueconsulting.sianroses.model.app.*;
import retrofit2.Call;
import retrofit2.http.*;

import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.AUTHORIZATION;
import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.DATA_TYPE;

/**
 * A class used by retrofit to make synchronization REST calls
 */
public interface SyncRestService {
	
	@GET("SyncMSSQL")
	Call<Response> fetch(@Header(AUTHORIZATION) String token);
	
	@POST("SyncMSSQL")
	Call<Response> post(@Header(AUTHORIZATION) String token, @Body Response response);
	
	@POST("SyncMSSQL")
	Call<Response> postCustomers(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body Response response);
	
	@POST("SyncMSSQL")
	Call<Response> postContacts(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body Response response);
	
	@POST("SyncMSSQL")
	Call<Response> postPriceList(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body Response response);
	
	@POST("SyncMSSQL")
	Call<Response> postProducts(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body Response response);
	
	@POST("SyncMSSQL")
	Call<Response> postProductChildren(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body Response response);
	
}
