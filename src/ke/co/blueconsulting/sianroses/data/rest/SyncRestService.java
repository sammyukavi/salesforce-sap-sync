package ke.co.blueconsulting.sianroses.data.rest;

import ke.co.blueconsulting.sianroses.model.app.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.AUTHORIZATION;


/**
 * An interface class used by retrofit to make REST calls
 */
public interface SyncRestService {
	
	@GET("SyncMSSQL")
	Call<ServerResponse> fetch(@Header(AUTHORIZATION) String token);
	
	@POST("api/index.php")
	Call<ServerResponse> post(@Header(AUTHORIZATION) String token, @Body ServerResponse serverResponse);
}
