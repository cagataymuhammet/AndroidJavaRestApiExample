package com.cagataymuhammet.retsapi.network;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    ServiceApi serviceApi;
    private static Retrofit retrofit;


    private static Retrofit getInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClientFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpClientFactory()
    {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        return builder.build();
    }

    public ServiceApi getServiceApi() {
        serviceApi = getInstance().create(ServiceApi.class);
        return serviceApi;
    }

}
