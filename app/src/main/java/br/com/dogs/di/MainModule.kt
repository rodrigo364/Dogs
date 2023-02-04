package br.com.dogs.di

import br.com.dogs.BuildConfig.*
import br.com.dogs.data.service.DogsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MainModule {

    @Singleton
    @Provides
    fun makeApiKeyInterceptor(): Interceptor {
        return Interceptor {
            var request = it.request()
            val url = request.url.newBuilder()
                .build()

            request = request.newBuilder()
                .header("X-RapidAPI-Key", XRAPID_API_KEY)
                .url(url).build()
            it.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if(DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(makeApiKeyInterceptor())
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    fun providesApiService(
        okHttpClient: OkHttpClient
        ) : DogsService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create())
            .build().create(DogsService::class.java)
    }
}