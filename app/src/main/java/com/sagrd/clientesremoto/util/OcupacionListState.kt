package com.sagrd.clientesremoto.util

import com.sagrd.clientesremoto.data.remote.dto.OcupacionDto

data class OcupacionListState(
    val isLoading: Boolean = false,
    val Ocupaciones: List<OcupacionDto> = emptyList(),
    val error: String = ""
)