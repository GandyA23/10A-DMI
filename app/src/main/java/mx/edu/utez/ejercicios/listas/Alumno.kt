package mx.edu.utez.ejercicios.listas

import java.io.Serializable

data class Alumno (
    var nombre : String? = null,
    var foto : String? = null,
    var estatus : String? = null,
    var edad : String? = null,
) : Serializable