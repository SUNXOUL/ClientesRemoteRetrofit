package com.sagrd.clientesremoto.ui.client

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.clientesremoto.data.repository.ClienteRepository
import com.sagrd.clientesremoto.data.repository.OcupacionRepository
import com.sagrd.clientesremoto.util.ClienteListState
import com.sagrd.clientesremoto.util.OcupacionListState
import com.sagrd.clientesremoto.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    var nombres: String by mutableStateOf("")
    var image by mutableStateOf<Uri?> (null)
    var email: String by mutableStateOf("")
    var telefono: String by mutableStateOf("")
    var celular: String by mutableStateOf("")
    var fechaNacimiento: String by mutableStateOf("")
    var direccion: String by mutableStateOf("")
    var ocupacionId : Int? by mutableStateOf(0)

    var nombresError: Boolean by mutableStateOf(true)
    var emailError: Boolean by mutableStateOf(true)
    var telefonoError: Boolean by mutableStateOf(true)
    var celularError: Boolean by mutableStateOf(true)
    var fechaNacimientoError: Boolean by mutableStateOf(true)
    var direccionError: Boolean by mutableStateOf(true)
    var ocupacionIdError : Boolean by mutableStateOf(true)


    fun onNombresChanged(value : String){
        nombres=value
        nombresError = value.isNullOrEmpty()
    }

    fun onEmailChanged(value : String){
        email=value
        emailError = value.isNullOrEmpty()
    }

    fun onTelefonoChanged(value : String){
        telefono=value
        telefonoError = value.isNullOrEmpty()
    }

    fun onCelularChanged(value : String){
        celular=value
        celularError = value.isNullOrEmpty()
    }

    fun onFechaNacimientoChanged(value : String){
        fechaNacimiento=value
        fechaNacimientoError = value.isNullOrEmpty()
    }

    fun onDireccionChanged(value : String){
        direccion=value
        direccionError = value.isNullOrEmpty()
    }
    fun onOcupacionChanged(value : Int?){
        ocupacionId=value
        ocupacionIdError = value != null
    }
    private var _state = mutableStateOf(ClienteListState())
    val state: State<ClienteListState> = _state

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

    init {
        clienteRepository.getClientes().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ClienteListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = ClienteListState(Clientes = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ClienteListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }

}