package mx.edu.utez.ejercicios.calculadoramvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.utez.ejercicios.calculadoramvvm.provider.CirculoProvider

class CirculoViewModel: ViewModel() {
    var result: MutableLiveData<String> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    fun mostrarPerimetro(radio: Double) {
        try {
            result.postValue("El perimetro del círculo es: P = ${String.format("%.2f", CirculoProvider.perimetro(radio))}")
        } catch (e : Exception) {
            error.postValue("Ha ocurrido un error, favor de verificar los datos")
        }
    }

    fun mostrarArea(radio: Double) {
        try {
            result.postValue("El área del círculo es: A = ${String.format("%.2f", CirculoProvider.area(radio))}")
        } catch (e : Exception) {
            error.postValue("Ha ocurrido un error, favor de verificar los datos")
        }
    }
}