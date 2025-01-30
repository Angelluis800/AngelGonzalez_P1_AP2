package edu.ucne.angelgonzalez_p1_ap2.presentation.entidad

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angelgonzalez_p1_ap2.data.local.dao.Dao
import edu.ucne.angelgonzalez_p1_ap2.data.local.entity.Entity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EntidadViewModel @Inject constructor(
    private val dao: Dao
) : ViewModel(){
    private val _uiState = MutableStateFlow(EntidadUiState())
    val uiState = _uiState.asStateFlow()
}

fun EntidadUiState.toEntity() = Entity(
    entityId = this.entityId
)