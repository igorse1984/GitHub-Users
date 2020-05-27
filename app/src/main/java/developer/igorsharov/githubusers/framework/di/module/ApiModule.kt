package developer.igorsharov.githubusers.framework.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import developer.igorsharov.githubusers.BASE_URL
import developer.igorsharov.githubusers.framework.retrofit.ApiServiceUsers
import developer.igorsharov.githubusers.framework.retrofit.ApiSourceUsers
import igor.sharov.data.PersistenceSourceUsers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiServiceUsers(retrofit: Retrofit): ApiServiceUsers =
        retrofit.create(ApiServiceUsers::class.java)

    @Provides
    @Singleton
    fun provideApiSourceUsers(apiServiceUsers: ApiServiceUsers): PersistenceSourceUsers =
        ApiSourceUsers(apiServiceUsers)
}