package ke.co.blueconsulting.sianroses.util;

import ke.co.blueconsulting.sianroses.data.RestServiceBuilder;
import ke.co.blueconsulting.sianroses.model.salesforce.ServerResponse;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public class ErrorUtil implements Serializable {
  
  public static ServerResponse parseError(Response<?> response) {
    
    Converter<ResponseBody, ServerResponse> converter = RestServiceBuilder.retrofit()
        .responseBodyConverter(ServerResponse.class, new Annotation[0]);
    
    ServerResponse error;
    
    try {
      error = converter.convert(response.errorBody());
    } catch (IOException e) {
      e.printStackTrace();
      ServerResponse serverResponse = new ServerResponse();
      //serverResponse.setData(new Data());
      //ArrayList<String> messages = new ArrayList<>();
      //messages.add(e.getMessage());
      //serverResponse.getData().setMessages(messages);
      return serverResponse;
    }
    
    return error;
  }
}
