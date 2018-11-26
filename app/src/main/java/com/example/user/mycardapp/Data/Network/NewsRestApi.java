package com.example.user.mycardapp.Data.Network;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class NewsRestApi {

    private static final String API_KEY = "821543713b4543be87a3c19dc1a6b8ca";
    private static final String BASE_URL = "http://api.nytimes.com/svc/topstories/v2/";
    private static final int TIMEOUT_IN_SECONDS = 2;
    private static NewsRestApi newsRestApi = new NewsRestApi();
    private final NewsApi api;

    private NewsRestApi () {
        OkHttpClient httpClient = buildOkHttpClient();
        Retrofit retrofit = buildRetrofit(httpClient);
        api = retrofit.create(NewsApi.class);
    }

    synchronized static NewsRestApi getInstance () {
        return newsRestApi;
    }

    NewsApi getApi () {
        return api;
    }

    @NonNull
    private OkHttpClient buildOkHttpClient () {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient().newBuilder()
                .addInterceptor(ApiKeyInterceptor.getInterceptor(API_KEY))
                .addInterceptor(interceptor)
                .connectTimeout(TIMEOUT_IN_SECONDS , TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS , TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS , TimeUnit.SECONDS)
                .build();
    }

    @NonNull
    private Retrofit buildRetrofit (@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
