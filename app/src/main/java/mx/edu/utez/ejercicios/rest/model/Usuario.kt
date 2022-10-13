package mx.edu.utez.ejercicios.rest.model

/** La data class debe de tener los mismo nombres que cada objeto en la respuesta de API**/
data class Usuario(
    var id : Long?,
    var name : String,
    var email : String,
    var gender : String,
    var status : String
)