package com.example.remotemediatorapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.remotemediatorapp.data.local.BeverageDatabase
import com.example.remotemediatorapp.data.local.BeverageEntity
import com.example.remotemediatorapp.data.remote.BeverageAPI
import com.example.remotemediatorapp.data.remote.BeverageRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeverageDB(@ApplicationContext context: Context): BeverageDatabase {
        return Room.databaseBuilder(
            context,
            BeverageDatabase::class.java,
            "beverage"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT))
            .build()
    }

    @Provides
    @Singleton
    fun provideBeverageApi(httpClient: OkHttpClient): BeverageAPI {
        return Retrofit.Builder()
            .baseUrl(BeverageAPI.BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BeverageAPI::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeveragePager(
        beverageDatabase: BeverageDatabase,
        beverageAPI: BeverageAPI
    ): Pager<Int, BeverageEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeverageRemoteMediator(beverageApi = beverageAPI, beverageDatabase),
            pagingSourceFactory = {
                beverageDatabase.dao.pagingSource()
            }

        )
    }
}