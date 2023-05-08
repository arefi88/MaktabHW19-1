package com.example.maktabhw19_1.di

import android.content.Context
import androidx.room.Room
import com.example.maktabhw19_1.datasource.DataSource
import com.example.maktabhw19_1.datasource.ILocalDataSource
import com.example.maktabhw19_1.local.LocalDataSource
import com.example.maktabhw19_1.local.MovieDao
import com.example.maktabhw19_1.local.MovieEntity
import com.example.maktabhw19_1.local.database.MovieDatabase
import com.example.maktabhw19_1.remote.RemoteDataSource
import com.example.maktabhw19_1.remote.api.MovieApiService
import com.example.maktabhw19_1.utils.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppMovieModule {

    @Provides
    @Singleton
    fun provideInterceptor()= Interceptor{ chain ->
        val url= chain.request()
            .url
            .newBuilder()
            //.addQueryParameter("api_key", API_KEY)
            //.addQueryParameter("language","en-US")
            .build()

        val request=chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor)=
        OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        //.client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit)=provideApi<MovieApiService>(retrofit)
    private inline fun <reified T> provideApi(retrofit: Retrofit):T{
        return retrofit.create(T::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService:MovieApiService):DataSource{
        return RemoteDataSource(apiService)
    }

//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit)=retrofit.create(MovieApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,MovieDatabase::class.java,
        "movie_db"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db:MovieDatabase)=db.getDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: MovieDao):ILocalDataSource=
        LocalDataSource(dao)

    @Provides
    @Singleton
    fun provideMovieEntity()=MovieEntity()

}