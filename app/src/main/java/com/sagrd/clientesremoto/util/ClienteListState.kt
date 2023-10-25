package com.sagrd.clientesremoto.util

import com.sagrd.clientesremoto.data.remote.dto.ClienteDto

data class ClienteListState(
    val isLoading: Boolean = false,
    val Clientes: List<ClienteDto> = emptyList(),
    val error: String = ""
)