package com.sagrd.clientesremoto.ui.ocupation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.clientesremoto.data.remote.dto.OcupacionDto
import com.sagrd.clientesremoto.data.repository.OcupacionRepository
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
class OcupacionViewModel @Inject constructor(
    private val ocupacionRepository: OcupacionRepository
) : ViewModel() {

    private var _state = mutableStateOf(OcupacionListState())
    val state: State<OcupacionListState> = _state

    var name by mutableStateOf("")
    var nameError by mutableStateOf(true)

    fun onNameChanged(value : String){
        name=value
        nameError = value.isNullOrEmpty()
    }

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

    init {
        ocupacionRepository.getOcupaciones().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = OcupacionListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = OcupacionListState(Ocupaciones = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = OcupacionListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun save(){
        viewModelScope.launch {
            if (!nameError){
                ocupacionRepository.postOcupacion(OcupacionDto(ocupationId = 0,name = name))
                clear()
            }
        }
    }
    fun clear(){
        name = ""
    }
}