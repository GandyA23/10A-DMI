package mx.edu.utez.ejercicios.mvp.interactor

import mx.edu.utez.ejercicios.mvp.presenter.MainPresenter


/**
 * (Model)
 * Se realizan las operaciones pesadas como operaciones de base de datos
 * Es necesario crear una interface para declarar sus funciones (buenas prácticas)
 * interactor solo se comunica con el presenter para mandarle datos a la vista
 *  */
class MainInteractor (private val presenter : MainPresenter) : MainInteractorInterface {
    override fun makeLogin(username: String, password: String) {
        if (username.isNullOrEmpty()) {
            presenter.showError("Ingresa un usuario")
        } else if (password.isNullOrEmpty()) {
            presenter.showError("Ingresa una contraseña")
        } else {
            presenter.showResult("Todo esta bien!")
        }
    }
}
