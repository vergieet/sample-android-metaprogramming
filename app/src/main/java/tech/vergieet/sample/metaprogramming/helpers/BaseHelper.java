package tech.vergieet.sample.metaprogramming.helpers;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class BaseHelper {

    public static final String BASE_URL = "https://metaprogramming-demo.herokuapp.com/api";
    private String url;

    BaseHelper(String url) {
        this.url = url;
    }


    public static BaseHelper create(String url) {
        return new BaseHelper(url);
    }

    public void getAll(Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + this.url)
                .build();
        client.newCall(request).enqueue(callback);
    }


    public void insert(Map<String, String> requestBody, Callback callback) {
        OkHttpClient client = new OkHttpClient();

        Request request = null;

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), new Gson().toJson(requestBody));

        request = new Request.Builder()
                .url(BASE_URL + this.url)
                .put(body)
                .build();

        client.newCall(request).enqueue(callback);
    }

}
