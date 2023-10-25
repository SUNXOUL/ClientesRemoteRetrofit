package com.sagrd.clientesremoto.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "Clientes")
data class ClienteDto(
    @PrimaryKey
    val clientId: Int? = null,
    var name: String = "",
    var image: String = "",
    var telephone: String = "",
    var cellphone: String = "",
    var email: String = "",
    var direccion: String = "",
    var birthDate: String = "",
    var ocupationId : Int? = null
)