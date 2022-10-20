package mx.edu.utez.ejercicios.datastore.model

data class UsuarioDatastore (
    var id: String?,
    var nombre: String,
    var paterno: String,
    var materno: String,
    var edad: Int,
    var sexo: String,
) {
    companion object {
        fun newElement (nombre: String, paterno: String, materno: String, edad: Int, sexo: String) : Map<String, Any> {
            return mapOf(
                "nombre" to nombre,
                "paterno" to paterno,
                "materno" to materno,
                "edad" to edad,
                "sexo" to sexo,
            )
        }
    }
}