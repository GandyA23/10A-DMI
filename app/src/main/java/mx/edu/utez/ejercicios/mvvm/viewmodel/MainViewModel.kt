package mx.edu.utez.ejercicios.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.utez.ejercicios.mvvm.provider.MainProvider

/**
 * Los ViewModel son los intermediarios, se encarga de pasar datos entre los providers y los views
 * */
class MainViewModel: ViewModel() {
    var result: MutableLiveData<String> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    fun checkLogin(user: String, password: String) {
        if (MainProvider.checkLogin(user, password)) {
            result.postValue("Los datos con correctos c:")
        } else {
            result.postValue("Favor de verificar sus datos")
        }
    }
}