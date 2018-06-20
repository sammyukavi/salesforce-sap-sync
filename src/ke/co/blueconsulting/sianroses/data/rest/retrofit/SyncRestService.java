package ke.co.blueconsulting.sianroses.data.rest.retrofit;

import ke.co.blueconsulting.sianroses.model.ServerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static ke.co.blueconsulting.sianroses.util.Constants.Units.BundleKeys.TOKEN;

/**
 * An interface class used by retrofit to make REST calls
 */
public interface SyncRestService {
  
  @GET("api")
  Call<ServerResponse> getServerData(@Query(TOKEN) String token);
  
}
