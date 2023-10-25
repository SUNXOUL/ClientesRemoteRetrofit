package com.sagrd.clientesremoto.data.remote

import com.sagrd.clientesremoto.data.remote.dto.OcupacionDto
import com.sagrd.clientesremoto.util.Resource
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OcupacionApi {

    @GET("/api/Ocupation")
    suspend fun getOCupaciones():List<OcupacionDto>

    @GET("/api/Ocupation/{OcupacionId}")
    suspend fun getOcupacionById(@Path("OcupacionId") OcupacionId: String): Response<OcupacionDto>

    @POST("/api/Ocupation")
    suspend fun postOcupacion(@Body ocupacion: OcupacionDto): Response<OcupacionDto>

}
