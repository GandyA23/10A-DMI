package mx.edu.utez.ejercicios.calculatormvp.interfaces

interface CalculatorInteractorInterface {
    fun suma (x : Double, y : Double)
    fun multiplicacion (x : Double, y : Double)
    fun division (x : Double, y : Double)
    fun validaDatos (x: Any, y: Any) : Boolean
}