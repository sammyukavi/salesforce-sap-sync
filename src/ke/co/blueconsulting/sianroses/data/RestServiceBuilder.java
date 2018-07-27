package ke.co.blueconsulting.sianroses.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ke.co.blueconsulting.sianroses.util.ErrorsDeserializer;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.*;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ke.co.blueconsulting.sianroses.util.Constants.SALESFORCE_API_BASE_URL;
import static ke.co.blueconsulting.sianroses.util.Constants.SALESFORCE_AUTH_BASE_URL;
import static ke.co.blueconsulting.sianroses.util.Constants.Units.*;

public class RestServiceBuilder {
	
	
	private static Retrofit.Builder builder;
	
	private static Retrofit retrofit;
	
	static {
		switchToSalesforceApiBaseUrl();
	}
	
	public static Retrofit retrofit() {
		return retrofit;
	}
	
	public static void switchToSalesforceApiBaseUrl() {
		builder = getRetrofitBuilder().baseUrl(SALESFORCE_API_BASE_URL);
		retrofit = builder.build();
	}
	
	public static void switchToSalesforceAuthUrl() {
		builder = getRetrofitBuilder().baseUrl(SALESFORCE_AUTH_BASE_URL);
		retrofit = builder.build();
	}
	
	private static GsonConverterFactory buildGsonConverter() {
		Gson gson = new GsonBuilder().registerTypeAdapter(ErrorsDeserializer.Errors.class, new ErrorsDeserializer()).create();
		return GsonConverterFactory.create(gson);
	}
	
	private static Retrofit.Builder getRetrofitBuilder() {
		return new Retrofit.Builder()
				.addConverterFactory(buildGsonConverter())
				.client(getUnsafeOkHttpClient());
	}
	
	private static OkHttpClient.Builder getOkHttpClientBuilder() {
		//add accept data type
		Interceptor acceptHeader = chain -> {
			Request request = chain.request().newBuilder()
					.addHeader("Accept", "application/json")
					.addHeader("Content-Type", "application/json;charset=UTF-8")
					.build();
			return chain.proceed(request);
		};
		
		//add logging
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		return new OkHttpClient.Builder().addInterceptor(acceptHeader)
				.addInterceptor(httpLoggingInterceptor)
				.readTimeout(READ_TIME_OUT_MINUTES, TimeUnit.MINUTES)
				.writeTimeout(WRITE_TIME_OUT_MINUTES, TimeUnit.MINUTES)
				.connectTimeout(CONNECT_TIME_OUT_MINUTES, TimeUnit.MINUTES)
				.retryOnConnectionFailure(true);
	}
	
	@SuppressWarnings("unused")
	private static OkHttpClient getSafeOkHttpClient() {
		return getOkHttpClientBuilder().build();
	}
	
	private static OkHttpClient getUnsafeOkHttpClient() {
		try {
			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init((KeyStore) null);
			TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
			if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
				throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
			}
			X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, new TrustManager[]{trustManager}, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			return getOkHttpClientBuilder().sslSocketFactory(sslSocketFactory, trustManager).hostnameVerifier((hostname, session) -> true)
					.retryOnConnectionFailure(true).build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	static <S> S createService(Class<S> serviceClass) {
		return retrofit.create(serviceClass);
	}
}
