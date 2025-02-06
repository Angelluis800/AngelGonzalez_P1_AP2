package edu.ucne.angelgonzalez_p1_ap2.presentation.entidad

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.angelgonzalez_p1_ap2.data.local.entity.SistemaEntity
import edu.ucne.angelgonzalez_p1_ap2.presentation.components.TopBar

@Composable
fun SistemaListScreen(
    viewModel: SistemaViewModel = hiltViewModel(),
    createSistema: () -> Unit,
    goToMenu: () -> Unit,
    goToSistema: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SistemaListBodyScreen(
        uiState,
        createSistema,
        goToMenu,
        goToSistema
    )
}

@Composable
fun SistemaListBodyScreen(
    uiState: SistemaUiState,
    createSistema: () -> Unit,
    goToMenu: () -> Unit,
    goToSistema: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(
                titulo = "Lista de Sistemas",
                onBackClick = { goToMenu() },
                onCreateClick = { createSistema() }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    SistemaHeaderRow()
                }
                items(uiState.sistemas) {
                    SistemaRow(
                        it,
                        goToSistema
                    )
                }
            }
        }
    }
}

@Composable
fun SistemaHeaderRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(6.dp, RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(50))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "ID",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                modifier = Modifier.weight(2f),
                text = "Nombre",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                modifier = Modifier.weight(2f),
                text = "Precio",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun SistemaRow(
    it: SistemaEntity,
    goToSistema: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                goToSistema(it.sistemaId!!)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = it.sistemaId.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier.weight(2f),
                text = it.nombre,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "$${it.precio}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}