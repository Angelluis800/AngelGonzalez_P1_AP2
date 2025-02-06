package edu.ucne.angelgonzalez_p1_ap2.presentation.entidad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.angelgonzalez_p1_ap2.presentation.components.ConfirmDeleting
import edu.ucne.angelgonzalez_p1_ap2.presentation.components.TopBar

@Composable
fun SistemaScreen(
    viewModel: SistemaViewModel = hiltViewModel(),
    sistemaId: Int,
    goBackToList: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SistemaBodyScreen(
        sistemaId = sistemaId,
        viewModel = viewModel,
        uiState = uiState,
        goBackToList = goBackToList
    )
}

@Composable
fun SistemaBodyScreen(
    sistemaId: Int,
    viewModel: SistemaViewModel,
    uiState: SistemaUiState,
    goBackToList: () -> Unit
) {
    LaunchedEffect(sistemaId) {
        if (sistemaId > 0) viewModel.find(sistemaId)
    }

    val openDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(if (sistemaId > 0) "Modificar Sistema" else "Registrar Sistema")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    OutlinedTextField(
                        label = { Text(text = "Nombre") },
                        value = uiState.nombre,
                        onValueChange = viewModel::onNombreChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(2.dp))

                    OutlinedTextField(
                        label = { Text(text = "Precio") },
                        value = uiState.precio,
                        onValueChange = viewModel::onPrecioChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(2.dp))

                    uiState.errorMessage?.let {
                        Text(text = it, color = Color.Red)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(onClick = { goBackToList() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Go back"
                            )
                            Text(text = "Atrás")
                        }
                        OutlinedButton(
                            onClick = {
                                if (sistemaId > 0) {
                                    openDialog.value = true
                                } else {
                                    viewModel.new()
                                }
                            },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = if (sistemaId > 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = if (sistemaId > 0) "Eliminar" else "Limpiar"
                            )
                            Text(text = if (sistemaId > 0) "Eliminar" else "Limpiar")
                        }
                        OutlinedButton(
                            onClick = {
                                if (viewModel.isValid()) {
                                    viewModel.save()
                                    goBackToList()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = if (sistemaId > 0) "Modificar" else "Guardar"
                            )
                            Text(text = if (sistemaId > 0) "Modificar" else "Guardar")
                        }
                    }
                }
            }
        }
    }
    ConfirmDeleting(
        openDialog = openDialog,
        onConfirm = {
            viewModel.delete()
            goBackToList()
        },
        onDismiss = {
            openDialog.value = false
        }
    )
}
