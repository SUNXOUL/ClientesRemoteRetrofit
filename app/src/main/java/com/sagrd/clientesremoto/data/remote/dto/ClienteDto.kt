package com.sagrd.clientesremoto.data.remote.dto

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class ClienteDto(
    @PrimaryKey
    val clienteId: Int? = null,
    var nombres: String = "",
    var image: String = "",
    var email: String = "",
    var telefono: String = "",
    var celular: String = "",
    var fechaNacimiento: String = "",
    var direccion: String = "",
    var ocupacionId : Int? = null
)