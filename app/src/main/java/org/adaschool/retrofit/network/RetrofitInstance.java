package org.adaschool.retrofit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.adaschool.retrofit.network.storage.JWTInterceptor;
import org.adaschool.retrofit.network.storage.Storage;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "https://dog.ceo/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(Storage tokenStorage) {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            JWTInterceptor jwtInterceptor = new JWTInterceptor(tokenStorage);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(jwtInterceptor)
                    .writeTimeout(0, TimeUnit.MILLISECONDS)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(1, TimeUnit.MINUTES).build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
                    .create();



            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(Java8CallAdapterFactory.create()) // Use Java 8 Call Adapter Factory
                    .build();
        }
        return retrofit;
    }
}

