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
	Call<Response> post(@Header(AUTHORIZATION) String token, @Body Response result);
	
	@POST("SyncMSSQL")
	Call<PushCustomer> postCustomers(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body PushCustomer result);
	
	@POST("SyncMSSQL")
	Call<PushCustomerContacts> postContacts(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body PushCustomerContacts result);
	
	@POST("SyncMSSQL")
	Call<PushPriceList> postPriceList(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body PushPriceList result);
	
	@POST("SyncMSSQL")
	Call<PushProduct> postProduct(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body PushProduct result);
	
}
