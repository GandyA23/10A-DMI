package mx.edu.utez.ejercicios.calculadoramvvm.provider

import kotlin.math.sqrt

class TrianguloProvider {
    companion object {
        fun perimetro (lado: Double) : Double {
            return lado * 3
        }

        fun area (lado: Double) : Double {
            return sqrt(3.toDouble()) / 4 * lado * lado
        }
    }
}