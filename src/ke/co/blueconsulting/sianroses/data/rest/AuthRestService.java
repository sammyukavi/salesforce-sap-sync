package ke.co.blueconsulting.sianroses.data.rest;

import ke.co.blueconsulting.sianroses.model.app.SalesforceAuthCredentials;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.*;

/**
 * A class used by retrofit to make authentication REST calls
 */
public interface AuthRestService {

    @POST("services/oauth2/token")
    @FormUrlEncoded
    Call<SalesforceAuthCredentials> authenticate(@Field(CLIENT_ID) String clientId, @Field(CLIENT_SECRET) String clientSecret,
            @Field(GRANT_TYPE) String grantType, @Field(USERNAME) String username,
            @Field(PASSWORD) String password);
}
