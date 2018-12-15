package msk.android.academy.javatemplate.NET;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class ApiKeyInterceptor implements Interceptor {

    private final String APP_KEY_PARAM = "app_key";
    private String APP_KEY;

    private final String APP_ID_PARAM = "app_id";
    private String APP_ID;

    final String APP_INGREDIENTS_PARAM = "q";

    private ApiKeyInterceptor(String apiId,String apiKey) {
        this.APP_KEY = apiKey;
        this.APP_ID = apiId;
    }


    public static Interceptor create(String apiId,String apiKey) {
        return new ApiKeyInterceptor(apiId,apiKey);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request requestWithoutApiKey = chain.request();

        final HttpUrl url = requestWithoutApiKey.url()
                .newBuilder()
                .addQueryParameter(APP_ID_PARAM, APP_ID)
                .addQueryParameter(APP_KEY_PARAM, APP_KEY)
                .build();

        final Request requestWithAttachedApiKey = requestWithoutApiKey.newBuilder()
                .url(url)
                .build();

        return chain.proceed(requestWithAttachedApiKey);
    }
}