package ke.co.blueconsulting.sianroses.data.rest;

import ke.co.blueconsulting.sianroses.model.app.ServerResponse;
import retrofit2.Call;
import retrofit2.http.*;

import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.*;


/**
 * An interface class used by retrofit to make REST calls
 */
public interface SyncRestService {
  
  @GET("api/index.php")
  Call<ServerResponse> fetch(@Query(TOKEN) String token);
  
  @POST("api/index.php")
  Call<ServerResponse> post(@Body ServerResponse serverResponse);
  
  @POST("services/oauth2/token")
  @FormUrlEncoded
  Call<ServerResponse> authenticate(@Field(CLIENT_ID) String clientId, @Field(CLIENT_SECRET) String clientSecret,
                                    @Field(GRANT_TYPE) String grantType, @Field(USERNAME) String username,
                                    @Field(PASSWORD) String password);
}
