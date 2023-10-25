package com.sagrd.clientesremoto.ui.client

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sagrd.clientesremoto.ui.ocupation.OcupacionViewModel
import com.sagrd.clientesremoto.util.Nav.AppScreens
import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar
import java.util.Date


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientesFormScreen(
    viewModel: ClienteViewModel = hiltViewModel(),
    navController: NavController,
    context: Context,
)
{
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collectLatest {
            if (it) {
                snackbarHostState.showSnackbar(
                    message = "Cliente guardado",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar Persona") },
                modifier = Modifier.shadow(16.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(route = AppScreens.ConsultScreen.route) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {
            ClientesForm(context=context,navController=navController,clienteviewModel = viewModel)
        }
    )
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ClientesForm(
    navController: NavController,
    ocupacionViewModel: OcupacionViewModel = hiltViewModel(),
    clienteviewModel: ClienteViewModel = hiltViewModel(),
    context: Context,
) {

    val ocupaciones = ocupacionViewModel.state.value.Ocupaciones


    var photoPickerLauncher =  rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri ->  clienteviewModel.image = uri})

    var expanded by remember { mutableStateOf(false) }

    var selectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            val keyboardController = LocalSoftwareKeyboardController.current

            OutlinedTextField(
                value = clienteviewModel.nombres,
                onValueChange = { clienteviewModel.onNombresChanged(it) },
                isError = clienteviewModel.nombresError,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Ingrese nombre") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = clienteviewModel.telefono,
                onValueChange = { clienteviewModel.onTelefonoChanged(it) },
                isError = clienteviewModel.telefonoError,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Ingrese numero de telefono") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = clienteviewModel.celular,
                onValueChange = {clienteviewModel.onCelularChanged(it) },
                isError = clienteviewModel.celularError,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Ingrese numero de telefono celular") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = clienteviewModel.email,
                onValueChange = { clienteviewModel.onEmailChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                isError = clienteviewModel.emailError,
                label = { Text("Ingrese Email") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = clienteviewModel.direccion,
                onValueChange = { clienteviewModel.onDireccionChanged(it)  },
                isError = clienteviewModel.direccionError,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Ingrese Direccion") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next)
            )
            showDatePicker(context = context,clienteviewModel=clienteviewModel)
            Spacer(modifier = Modifier.height(15.dp))

            Column {
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = {  },
                    isError = clienteviewModel.ocupacionIdError,
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    label = { Text("Selecciona la ocupacion") },
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                ) {
                    ocupaciones.forEach { ocupation ->
                        DropdownMenuItem(text = { Text(text = ocupation.nombre)}, onClick = {
                            clienteviewModel.onOcupacionChanged(ocupation.ocupacionId)
                            selectedText=ocupation.nombre
                            expanded = !expanded
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = clienteviewModel.image?.lastPathSegment.toString(),
                    onValueChange = { },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { IconButton(onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }) {
                        Icon(imageVector = Icons.Filled.Image, contentDescription ="imagen" )
                    }
                    },
                    label = { Text("Ingrese Imagen") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = {
                keyboardController?.hide()

                    clienteviewModel.setMessageShown()
                    selectedText=""

            }, modifier = Modifier
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Default.Done, contentDescription ="Done" )
            }
        }

    }
}
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDatePicker(
    context: Context,
    clienteviewModel: ClienteViewModel = hiltViewModel(),
) {

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()


    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            clienteviewModel.onFechaNacimientoChanged( "$dayOfMonth/$month/$year")
        }, year, month, day
    )
    OutlinedTextField(
        value = clienteviewModel.fechaNacimiento,
        onValueChange = { },
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        isError = clienteviewModel.fechaNacimientoError,
        leadingIcon = { IconButton(onClick = {
            datePickerDialog.show()
        }) {
            Icon(imageVector = Icons.Filled.DateRange, contentDescription ="date" )
        }
        },
        label = { Text("Ingrese Fecha de Nacimiento") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next)
    )

}