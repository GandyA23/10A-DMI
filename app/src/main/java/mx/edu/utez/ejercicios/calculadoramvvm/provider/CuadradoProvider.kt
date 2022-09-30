package mx.edu.utez.ejercicios.calculadoramvvm.provider

class CuadradoProvider {
    companion object {
        fun perimetro (lado : Double) : Double {
            return lado * 4
        }

        fun area (lado: Double) : Double {
            return lado * lado
        }
    }
}