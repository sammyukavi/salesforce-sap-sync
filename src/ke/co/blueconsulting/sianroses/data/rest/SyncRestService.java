package ke.co.blueconsulting.sianroses.data.rest;

import ke.co.blueconsulting.sianroses.model.app.PushContacts;
import ke.co.blueconsulting.sianroses.model.app.PushCustomer;
import ke.co.blueconsulting.sianroses.model.app.Result;
import retrofit2.Call;
import retrofit2.http.*;

import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.AUTHORIZATION;
import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.DATA_TYPE;

/**
 * A class used by retrofit to make synchronisation REST calls
 */
public interface SyncRestService {

    @GET("SyncMSSQL")
    Call<Result> fetch(@Header(AUTHORIZATION) String token);

    @POST("SyncMSSQL")
    Call<Result> post(@Header(AUTHORIZATION) String token, @Body Result result);

    @POST("SyncMSSQL")
    Call<Result> postCustomers(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body PushCustomer result);

    @POST("SyncMSSQL")
    Call<Result> postContacts(@Header(AUTHORIZATION) String token, @Query(DATA_TYPE) String dataType, @Body PushContacts result);
}
