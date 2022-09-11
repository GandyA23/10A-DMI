package mx.edu.utez.ejercicios.vistas

import java.io.Serializable

/*
* Es necesario colocar el Serializable para mandarlo entre Activities*/
data class Persona (
    var nombre : String? = null,
    var apellidos : String? = null,
    var edad : String? = null
) : Serializable