package com.sagrd.clientesremoto.di

import android.content.Context
import androidx.room.Room
import com.sagrd.clientesremoto.data.remote.AuthInterceptor
import com.sagrd.clientesremoto.data.remote.ClientesApi
import com.sagrd.clientesremoto.data.remote.OcupacionApi
import com.sagrd.clientesremoto.data.repository.ClienteRepository
import com.sagrd.clientesremoto.data.repository.OcupacionRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    @Provides
    @Singleton
    fun provideClienteRepository(clienteApi: ClientesApi): ClienteRepository {
        return ClienteRepository(clienteApi)
    }
    @Provides
    @Singleton
    fun provideOcupacionRepository(ocupacionApi: OcupacionApi): OcupacionRepository {
        return OcupacionRepository(ocupacionApi)
    }

    @Provides
    @Singleton
    fun provideClientesApi(moshi: Moshi, ): ClientesApi {
        return Retrofit.Builder()
            .baseUrl("https://ticketclientes.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ClientesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOcupacionesApi(moshi: Moshi, ): OcupacionApi {
        return Retrofit.Builder()
            .baseUrl("https://ticketclientes.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(OcupacionApi::class.java)
    }
}