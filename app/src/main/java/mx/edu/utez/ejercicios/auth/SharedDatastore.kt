package mx.edu.utez.ejercicios.auth

import android.content.Context

class SharedDatastore(context : Context) {
    val NOMBRE_DOCUMENTO = "mx.edu.utez.ejercicios"

    val EMAIL = "email"
    val PROVIDER = "provider"

    /** 0 en el segundo parámetros significa privado +*/
    var storage = context.getSharedPreferences(NOMBRE_DOCUMENTO, 0)

    /**
     * Iniciar sesión
     * */
    fun save (email: String, provider: String) {
        storage.edit().putString(EMAIL, email).apply()
        storage.edit().putString(PROVIDER, provider).apply()
    }

    /**
     * Consultar el usuario logueado
     * */
    fun get () : HashMap<String, String> = hashMapOf(
        EMAIL to storage.getString(EMAIL, "")!!,
        PROVIDER to storage.getString(PROVIDER, "")!!,
    )

    /**
     * Elimina todos los usuarios en el context
     * */
    fun delete () {
        storage.edit().clear().apply()
    }
}