package mx.edu.utez.ejercicios.calculadoramvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.utez.ejercicios.calculadoramvvm.provider.CuadradoProvider

class CuadradoViewModel: ViewModel() {
    var result: MutableLiveData<String> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    fun mostrarPerimetro(lado: Double) {
        try {
            result.postValue("El perimetro del cuadrado es: P = ${String.format("%.2f", CuadradoProvider.perimetro(lado))}")
        } catch (e : Exception) {
            error.postValue("Ha ocurrido un error, favor de verificar los datos")
        }
    }

    fun mostrarArea(lado: Double) {
        try {
            result.postValue("El área del cuadrado es: A = ${String.format("%.2f", CuadradoProvider.area(lado))}")
        } catch (e : Exception) {
            error.postValue("Ha ocurrido un error, favor de verificar los datos")
        }
    }
}