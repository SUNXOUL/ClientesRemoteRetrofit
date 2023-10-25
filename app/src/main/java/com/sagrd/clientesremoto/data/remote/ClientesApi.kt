package com.sagrd.clientesremoto.data.remote


import com.sagrd.clientesremoto.data.remote.dto.ClienteDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientesApi {

    @GET("/api/client")
    suspend fun getClientes():List<ClienteDto>

    @GET("/api/client/{clieteId}")
    suspend fun getClienteById(@Path("clienteId") clienteId: Int): Response<ClienteDto>

    @POST("/api/client")
    suspend fun postCliente(@Body cliente: ClienteDto): Response<ClienteDto>

    @DELETE("/api/Clientes/{id}")
    suspend fun deleteCliente(@Path("id") id: Int):Response<ClienteDto>
}

