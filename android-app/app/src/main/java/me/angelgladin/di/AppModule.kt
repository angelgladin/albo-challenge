package me.angelgladin.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import me.angelgladin.data.AppDatabase
import me.angelgladin.data.BeerService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BeerService.BASE_URL)
            // TODO: ponerlo mejor
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }).build())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun provideBeerService(retrofit: Retrofit): BeerService =
        retrofit.create(BeerService::class.java)

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext appContext: Context) = AppDatabase.getInstance(appContext)

    @Provides
    fun provideBeerDao(db : AppDatabase) = db.beersDao()

    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

}