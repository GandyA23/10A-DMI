package mx.edu.utez.ejercicios.mvp.presenter

import mx.edu.utez.ejercicios.mvp.MainMvpActivity
import mx.edu.utez.ejercicios.mvp.interactor.MainInteractor

/**
 * (Presenter - Intermediario)
 * El presenter estará encargado de comunicarse entre el interactor y la vista
 * */
class MainPresenter(private val view : MainMvpActivity) : MainPresenterInterface {

    val interactor = MainInteractor(this)

    override fun makeLogin(username: String, password: String) {
        interactor.makeLogin(username, password)
    }

    override fun showError(error: String) {
        view.showError(error)
    }

    override fun showResult(result: String) {
        view.showResult(result)
    }
}