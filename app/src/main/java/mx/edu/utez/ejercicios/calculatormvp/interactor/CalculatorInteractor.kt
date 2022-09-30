package mx.edu.utez.ejercicios.calculatormvp.interactor

import android.util.Log
import mx.edu.utez.ejercicios.calculatormvp.interfaces.CalculatorInteractorInterface
import mx.edu.utez.ejercicios.calculatormvp.presenter.CalculatorPresenter
import java.lang.Exception

class CalculatorInteractor (private val  presenter: CalculatorPresenter) : CalculatorInteractorInterface {
    companion object {
        const val ERROR_MSG = "Favor de ingresar valores válidos";
    }

    override fun suma(x: Double, y: Double) {
        try {
            if (validaDatos(x, y))
                presenter.showResult(x + y)
        } catch (e : Exception) {
            presenter.showError(ERROR_MSG)
        }
    }

    override fun multiplicacion(x: Double, y: Double) {
        try {
            if (validaDatos(x, y))
                presenter.showResult(x * y)
        } catch (e : Exception) {
            presenter.showError(ERROR_MSG)
        }
    }

    override fun division(x: Double, y: Double) {
        try {
            if (validaDatos(x, y))
                presenter.showResult(x / y)
        } catch (e : Exception) {
            presenter.showError(ERROR_MSG)
        }
    }

    override fun validaDatos(x: Any, y: Any) : Boolean {
        try {
            return x is Double && y is Double
        } catch (e : Exception) {
            Log.e("Error: ", "Ha fallado algo")
        }
        return false
    }
}