package mx.edu.utez.ejercicios.rest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.rest.Usuario
import mx.edu.utez.ejercicios.rest.provider.ConsultaProvider

class ConsultaViewModel: ViewModel() {
    var listado : MutableLiveData<List<Usuario>> = MutableLiveData()
    var error : MutableLiveData<String> = MutableLiveData()
    var isApiProgress : MutableLiveData<Boolean> = MutableLiveData()

    fun getAll() {
        viewModelScope.launch {
            isApiProgress.value = true

            val response = ConsultaProvider.getAll()

            if (response.isNotEmpty())
                listado.value = response
            else
                error.value = "La lista esta vacía"

            isApiProgress.value = false
        }
    }
}
