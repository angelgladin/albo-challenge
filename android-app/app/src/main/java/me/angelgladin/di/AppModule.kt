package me.angelgladin.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import me.angelgladin.data.AppDatabase
import me.angelgladin.data.BeerService
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

}