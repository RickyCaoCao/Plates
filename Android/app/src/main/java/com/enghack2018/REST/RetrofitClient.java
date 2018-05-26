package com.enghack2018.REST;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.Converter.Factory;
import timber.log.Timber;

/**
 * RetrofitClient wrapper class
 */
public class RetrofitClient {

    public static Retrofit buildRetrofitClient(String baseUrl, Factory factory){
        //For debugging
        Timber.plant(new Timber.DebugTree());
        //Http Logging interceptor
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(factory);
        return builder.build();
    }
}
