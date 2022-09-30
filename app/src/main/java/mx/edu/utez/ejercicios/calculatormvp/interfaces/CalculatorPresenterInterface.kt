package mx.edu.utez.ejercicios.calculatormvp.interfaces

interface CalculatorPresenterInterface {
    fun suma (x : Double, y : Double)
    fun multiplicacion (x : Double, y : Double)
    fun division (x : Double, y : Double)
    fun showError (error : String)
    fun showResult (result : Double)
}