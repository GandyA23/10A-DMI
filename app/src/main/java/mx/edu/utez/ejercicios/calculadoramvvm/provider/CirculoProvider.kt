package mx.edu.utez.ejercicios.calculadoramvvm.provider

class CirculoProvider {
    companion object {
        fun perimetro (radio : Double) : Double {
            return 2 * Math.PI * radio
        }

        fun area (radio: Double) : Double {
            return Math.PI * radio * radio
        }
    }
}