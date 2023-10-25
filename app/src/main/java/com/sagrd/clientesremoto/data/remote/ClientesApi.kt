package com.sagrd.clientesremoto.data.remote

import com.sagrd.clientesremoto.data.remote.dto.ClienteDto
import com.sagrd.clientesremoto.util.Resource
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientesApi {

    @GET("/api/client")
    suspend fun getClientes():Response<Resource<List<ClienteDto>>>

    @GET("/api/client{ClieteId}")
    suspend fun getClienteById(@Path("ClienteId") clienteId: String): Response<ClienteDto>

    @POST("/api/client")
    suspend fun postCliente(@Body cliente: ClienteDto): Response<ClienteDto>
}

