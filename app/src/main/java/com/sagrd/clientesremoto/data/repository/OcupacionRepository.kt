package com.sagrd.clientesremoto.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.sagrd.clientesremoto.data.remote.OcupacionApi
import com.sagrd.clientesremoto.data.remote.dto.ClienteDto
import com.sagrd.clientesremoto.data.remote.dto.OcupacionDto
import com.sagrd.clientesremoto.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class OcupacionRepository @Inject constructor(
    private val api: OcupacionApi
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getOcupaciones(): Flow<Resource<List<OcupacionDto>?>> = flow {
        try {
            emit(Resource.Loading())

            val ocupaciones = api.getOCupaciones()

            emit(Resource.Success(ocupaciones))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
    suspend fun postOcupacion(ocupacion : OcupacionDto) : OcupacionDto?{
        val response = api.postOcupacion(ocupacion)
        if (response.isSuccessful) {
            response.body()
        }
        return ocupacion
    }
}