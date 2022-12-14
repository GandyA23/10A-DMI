package mx.edu.utez.ejercicios.rest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.rest.model.Usuario
import mx.edu.utez.ejercicios.rest.datatype.ResultType
import mx.edu.utez.ejercicios.rest.provider.ConsultaProvider

class ConsultaViewModel: ViewModel() {
    var listado : MutableLiveData<List<Usuario>> = MutableLiveData()
    var data : MutableLiveData<Usuario> = MutableLiveData()
    var error : MutableLiveData<String> = MutableLiveData()
    var isApiProgress : MutableLiveData<Boolean> = MutableLiveData()

    fun getAll() {
        viewModelScope.launch {
            isApiProgress.value = true

            val response = ConsultaProvider.getAll()

            if (response.resultType == ResultType.SUCCESS)
                listado.value = response.data!!
            else
                error.value = response.error!!

            isApiProgress.value = false
        }
    }

    fun show(id: Long) {
        viewModelScope.launch {
            isApiProgress.value = true

            val response = ConsultaProvider.show(id)

            if (response.resultType == ResultType.SUCCESS)
                data.value = response.data!!
            else
                error.value = response.error!!

            isApiProgress.value = false
        }
    }
}
