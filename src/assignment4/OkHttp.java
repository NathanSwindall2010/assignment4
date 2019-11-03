package assignment4;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {

    // one instance, reuse


    public static String sendGet(String url) throws Exception {

    	OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                //.addHeader("custom-key", "mkyong")  // add request headers
                //.addHeader("User-Agent", "OkHttp Bot")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            return response.body().string();
        }
    }
}
