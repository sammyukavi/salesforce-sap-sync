package ke.co.blueconsulting.sianroses.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ke.co.blueconsulting.sianroses.util.Constants.SALESFORCE_AUTH_BASE_URL;
import static ke.co.blueconsulting.sianroses.util.Constants.SALESFORCE_API_BASE_URL;
import static ke.co.blueconsulting.sianroses.util.Constants.Units.CONNECT_TIME_OUT_SECONDS;
import static ke.co.blueconsulting.sianroses.util.Constants.Units.READ_TIME_OUT_SECONDS;

public class RestServiceBuilder {
	
	private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
	
	private static Retrofit.Builder builder;
	
	private static Retrofit retrofit;
	
	static {
		switchToSalesforceBaseUrl();
	}
	
	static <S> S createService(Class<S> serviceClass) {
		OkHttpClient okHttpClient = getHttpClient();
		retrofit = builder.client(okHttpClient).build();
		return retrofit.create(serviceClass);
	}
	
	private static OkHttpClient getHttpClient() {
		//add logging
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		//add accept data type
		Interceptor acceptHeader = new Interceptor() {
			
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request().newBuilder()
						.addHeader("Accept", "application/json")
						.addHeader("Content-Type", "application/json;charset=UTF-8")
						.build();
				return chain.proceed(request);
			}
		};
		okHttpClientBuilder.addInterceptor(acceptHeader)
				.addInterceptor(httpLoggingInterceptor)
				.readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
				.connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
				.retryOnConnectionFailure(true)
				.build();
		return okHttpClientBuilder.build();
	}
	
	private static GsonConverterFactory buildGsonConverter() {
		Gson gson = new GsonBuilder().setLenient().create();
		return GsonConverterFactory.create(gson);
	}
	
	public static Retrofit retrofit() {
		return retrofit;
	}
	
	public static void switchToSalesforceBaseUrl() {
		builder =
				new Retrofit.Builder()
						.addConverterFactory(buildGsonConverter())
						.baseUrl(SALESFORCE_API_BASE_URL)
						.client(getHttpClient());
	}
	
	public static void switchToSalesforceAuthUrl() {
		builder =
				new Retrofit.Builder()
						.addConverterFactory(buildGsonConverter())
						.baseUrl(SALESFORCE_AUTH_BASE_URL)
						.client(getHttpClient());
	}
	
}
