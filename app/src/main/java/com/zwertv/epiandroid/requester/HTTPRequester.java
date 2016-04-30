package com.zwertv.epiandroid.requester;

import android.webkit.HttpAuthHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Elliott on 28/01/2016.
 */
public class HTTPRequester implements IRequester {
    public class HTTPResponse implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("Login failure", e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final Request copy = response.request().newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            Log.d("Buffer content", buffer.readUtf8());
            Log.d("Login headers", call.request().toString());
            Log.d("Login Response", response.body().string());
        }
    }

    private static HTTPRequester ourInstance = null;
    private OkHttpClient httpClient = new OkHttpClient();
    public static HTTPRequester getInstance() {
        if (ourInstance == null)
            ourInstance = new HTTPRequester();
        return ourInstance;
    }

    private static MediaType encodedUrl = MediaType.parse("application/x-www-form-urlencoded");

    private HTTPRequester() {
    }

    public String get(String url, String body) throws RequesterException {
        if (url == null || url.isEmpty() || body == null)
            throw new  RequesterException("post() url or body null");
        Request request = new Request.Builder().url(url + "?" + body).addHeader("Content-type", "x-www-form-urlencoded").addHeader("cache-control", "no-cache").build();
        String result = "";
        try {
            Response response = httpClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            throw new RequesterException("post() httpClient error : " + e.getMessage());
        }
        return result;
    }

    public String post(String url, String body) throws RequesterException {
        if (url == null || url.isEmpty() || body == null)
            throw new  RequesterException("post() url or body null");
        RequestBody rbody = RequestBody.create(encodedUrl, body);
        Request request = new Request.Builder().url(url).addHeader("Content-type", "x-www-form-urlencoded").addHeader("cache-control", "no-cache").post(rbody).build();
        String result = "";
        try {
            Response response = httpClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            throw new RequesterException("post() httpClient error : " + e.getMessage());
        }
        return result;
    }
}
