package mx.edu.utez.ejercicios.mvp.presenter

interface MainPresenterInterface {
    fun makeLogin (username: String, password: String)
    fun showError (error : String)
    fun showResult (result : String)
}