package mx.edu.utez.ejercicios.rest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.rest.model.Usuario
import mx.edu.utez.ejercicios.rest.datatype.ResultType
import mx.edu.utez.ejercicios.rest.provider.RegistroProvider

class RegistroViewModel : ViewModel() {

    var data : MutableLiveData<Usuario> = MutableLiveData()
    var error : MutableLiveData<String> = MutableLiveData()
    var isApiProgress : MutableLiveData<Boolean> = MutableLiveData()

    fun store(usuario: Usuario) {
        viewModelScope.launch {
            isApiProgress.value = true

            val response = RegistroProvider.registro(usuario)

            if (response.resultType == ResultType.SUCCESS)
                data.value = response.data!!
            else
                error.value = response.error!!

            isApiProgress.value = false
        }
    }
}