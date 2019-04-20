package com.example.cholomanglicmot.nativechickenandduck;



import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class APIHelper {

    private final static String BASE_URL = "http://192.168.254.105:8080/api/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public APIHelper(){

    }
    //Brooder
    public static void addBrooderFamily(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addBrooderInventory(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addBrooderFeeding(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addBrooderGrowth(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    //Replacement
    public static void addReplacementFamily(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addReplacementInventory(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addReplacementFeeding(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addReplacementGrowth(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    //Breeder
    public static void addBreederFamily(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addBreederInventory(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addBreederFeeding(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addEggProduction(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addHatcheryRecords(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addEggQuality(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
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
    public static void addMortalityAndSales(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addPhenoMorphos(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void addPhenoMorphoValues(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), request, responseHandler);
    }





    public static void getFarmID(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getFarmInfo(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getFarmBatchingWeek(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getPen(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getGeneration(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getLine(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getFamily(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getFamilyForDisplay(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getPhenoMorphos(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getPhenoMorphoValues(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    //Brooder
    public static void getBrooder(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getBrooderInventory(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getBrooderFeeding(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getBrooderGrowth(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    //Replacement
    public static void getReplacement(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getReplacementInventory(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getReplacementFeeding(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getReplacementGrowth(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    //Breeder
    public static void getBreeder(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getBreederInventory(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getBreederFeeding(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getEggProduction(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getHatcheryRecords(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    public static void getEggQuality(String url, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), responseHandler);
    }



    //UPDATE
    public static void editPen(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.patch(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void editPenCount(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.patch(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void editBrooderInventoryMaleFemale(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.patch(getAbsoluteUrl(url), request, responseHandler);
    }
    public static void editReplacementInventoryMaleFemale(String url, RequestParams request, AsyncHttpResponseHandler responseHandler){
        client.patch(getAbsoluteUrl(url), request, responseHandler);
    }
    //URL
    private static String getAbsoluteUrl(String url){
        return BASE_URL + url;
    }

}
