package com.example.user.mycardapp.Data.Network;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    private static final String API_KEY = "api-key";
    private final String apiKey;

    private ApiKeyInterceptor (@NonNull String apiKey) {
        this.apiKey = apiKey;
    }

    static Interceptor getInterceptor (@NonNull String apiKey) {
        return new ApiKeyInterceptor(apiKey);
    }

    @Override
    public Response intercept (@NonNull Chain chain) throws IOException {
        final Request requestWithoutApiKey = chain.request();

        final HttpUrl url = requestWithoutApiKey.url()
                .newBuilder()
                .addQueryParameter(API_KEY , apiKey)
                .build();
        Request requestWithApiKey = requestWithoutApiKey.newBuilder()
                .url(url)
                .build();
        return chain.proceed(requestWithApiKey);
    }

}
