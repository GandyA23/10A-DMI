package mx.edu.utez.ejercicios.calculatormvp.presenter

import mx.edu.utez.ejercicios.calculatormvp.CalculatorActivity
import mx.edu.utez.ejercicios.calculatormvp.interactor.CalculatorInteractor
import mx.edu.utez.ejercicios.calculatormvp.interfaces.CalculatorPresenterInterface

class CalculatorPresenter(private val view : CalculatorActivity) : CalculatorPresenterInterface {

    val interactor = CalculatorInteractor(this)

    override fun suma(x: Double, y: Double) {
        interactor.suma(x, y)
    }

    override fun multiplicacion(x: Double, y: Double) {
        interactor.multiplicacion(x, y)
    }

    override fun division(x: Double, y: Double) {
        interactor.division(x, y)
    }

    override fun showError(error: String) {
        view.showError(error)
    }

    override fun showResult(result: Double) {
        view.showResult(result)
    }
}