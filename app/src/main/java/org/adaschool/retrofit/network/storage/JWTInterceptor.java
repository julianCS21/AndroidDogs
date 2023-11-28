package org.adaschool.retrofit.network.storage;

import org.adaschool.retrofit.network.storage.Storage;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class JWTInterceptor implements Interceptor {
    private Storage tokenStorage;

    public JWTInterceptor(Storage tokenStorage) {
        this.tokenStorage = tokenStorage;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        String token = tokenStorage.getToken();

        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
