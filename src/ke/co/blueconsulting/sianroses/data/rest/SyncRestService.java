package ke.co.blueconsulting.sianroses.data.rest;

import ke.co.blueconsulting.sianroses.model.app.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.TOKEN;


/**
 * An interface class used by retrofit to make REST calls
 */
public interface SyncRestService {
	
	@GET("api/index.php")
	Call<ServerResponse> fetch(@Query(TOKEN) String token);
	
	@POST("api/index.php")
	Call<ServerResponse> post(@Body ServerResponse serverResponse);
}
