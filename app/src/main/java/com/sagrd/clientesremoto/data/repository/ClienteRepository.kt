package com.sagrd.clientesremoto.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.sagrd.clientesremoto.data.remote.ClientesApi
import com.sagrd.clientesremoto.data.remote.dto.ClienteDto
import com.sagrd.clientesremoto.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val api: ClientesApi
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getClientes(): Flow<Resource<List<ClienteDto>?>> = flow {
        try {
            emit(Resource.Loading())

            val clients = api.getClientes()

            emit(Resource.Success(clients))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun postCliente(cliente : ClienteDto) : ClienteDto?{
                val response = api.postCliente(cliente)
                if (response.isSuccessful) {
                    response.body()
                }
            return cliente
    }


    suspend fun deleteCliente(id: Int) : ClienteDto? {
        return api.deleteCliente(id).body()
    }
}