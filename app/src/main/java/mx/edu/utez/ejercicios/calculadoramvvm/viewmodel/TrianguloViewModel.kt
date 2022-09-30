package mx.edu.utez.ejercicios.calculadoramvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.utez.ejercicios.calculadoramvvm.provider.CirculoProvider
import mx.edu.utez.ejercicios.calculadoramvvm.provider.TrianguloProvider

class TrianguloViewModel: ViewModel() {
    var result: MutableLiveData<String> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    fun mostrarPerimetro(lado: Double) {
        try {
            result.postValue("El perimetro del triangulo es: P = ${String.format("%.2f", TrianguloProvider.perimetro(lado))}")
        } catch (e : Exception) {
            error.postValue("Ha ocurrido un error, favor de verificar los datos")
        }
    }

    fun mostrarArea(lado: Double) {
        try {
            result.postValue("El área del triangulo es: A = ${String.format("%.2f", TrianguloProvider.area(lado))}")
        } catch (e : Exception) {
            error.postValue("Ha ocurrido un error, favor de verificar los datos")
        }
    }
}