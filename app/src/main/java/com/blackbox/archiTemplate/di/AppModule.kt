package com.blackbox.archiTemplate.di


import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import com.blackbox.archiTemplate.App
import com.blackbox.archiTemplate.data.local.db.AppDatabase
import com.blackbox.archiTemplate.data.network.APIs
import com.blackbox.archiTemplate.utils.Constants
import com.blackbox.archiTemplate.utils.LiveDataCallAdapterFactory
import com.blackbox.archiTemplate.utils.RxBus
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Created by umair on 10/01/2018.
 */

@Module(includes = [AndroidInjectionModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d("Retrofit", message)
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .build()

    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): APIs {
        return retrofit.create(APIs::class.java)
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(): Executor {
        return Executors.newFixedThreadPool(2)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "mydb")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideRxBus(): RxBus {
        return RxBus()
    }
}
