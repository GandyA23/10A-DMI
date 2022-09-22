package mx.edu.utez.ejercicios.shared

import android.content.Context


/**
 * Shared nos ayuda a manejar sesiones dentro de una app
 * */
class Shared(context : Context) {
    val NOMBRE_DOCUMENTO = "mx.edu.utez.ejercicios"

    val USERNAME = "username"
    val PASSWORD = "password"

    /** 0 en el segundo parámetros significa privado +*/
    var storage = context.getSharedPreferences(NOMBRE_DOCUMENTO, 0)

    /**
     * Iniciar sesión
     * */
    fun save (username: String, password: String) {
        storage.edit().putString(USERNAME, username).apply()
        storage.edit().putString(PASSWORD, password).apply()
    }

    /**
     * Consultar el usuario logueado
     * */
    fun get () : String = storage.getString(USERNAME, "")!!

    /**
     * Elimina todos los usuarios en el context
     * */
    fun delete () {
        storage.edit().clear().apply()
    }
}