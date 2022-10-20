package mx.edu.utez.ejercicios.datastore.model

class UsuarioFullDatastore(
    var id: String,
    nombre: String,
    paterno: String,
    materno: String,
    edad: Int,
    sexo: String,
) : UsuarioDatastore(nombre, paterno, materno, edad, sexo)