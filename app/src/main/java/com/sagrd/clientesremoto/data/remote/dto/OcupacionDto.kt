package com.sagrd.clientesremoto.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class OcupacionDto(
    @PrimaryKey
    val ocupacionId: Int? = null,
    var nombre: String = ""
)