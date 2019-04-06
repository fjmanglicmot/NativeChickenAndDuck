package com.example.cholomanglicmot.nativechickenandduck;



import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class APIHelper {

    private final static String BASE_URL = "http://192.168.254.109:8080/api/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public APIHelper(){

    }

    public static void addBrooderFamily(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addGeneration(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addLine(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addFamily(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addPen(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){

        client.post(getAbsoluteUrl(url), request, responseHandler);
    }



    //URL
    private static String getAbsoluteUrl(String url){
        return BASE_URL + url;
    }

}
