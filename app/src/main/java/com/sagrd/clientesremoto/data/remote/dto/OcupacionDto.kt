package com.sagrd.clientesremoto.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "Clientes")
data class OcupacionDto(
    @PrimaryKey
    val ocupationId: Int? = null,
    var name: String = ""
)