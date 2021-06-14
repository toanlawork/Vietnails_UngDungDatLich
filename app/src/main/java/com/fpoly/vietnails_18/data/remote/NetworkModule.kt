package com.fpoly.vietnails_18.data.remote
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L
    private fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url().newBuilder()
                    .build()
            val newRequest = request.newBuilder()
                    .url(newUrl)
                    .method(request.method(), request.body())
                    .build()
            chain.proceed(newRequest)
        }
    }

    fun provideAPI(): Retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun provideHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(logging)
        clientBuilder.addInterceptor(provideHeaderInterceptor())
        return clientBuilder.build()
    }
}