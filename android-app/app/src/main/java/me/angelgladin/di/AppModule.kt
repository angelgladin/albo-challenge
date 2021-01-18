package me.angelgladin.di

import com.squareup.moshi.Moshi
import dagger.Provides
import me.angelgladin.data.BeerService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BeerService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun provideBeerService(retrofit: Retrofit): BeerService =
        retrofit.create(BeerService::class.java)

}