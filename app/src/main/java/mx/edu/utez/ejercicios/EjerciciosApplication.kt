package mx.edu.utez.ejercicios

import android.app.Application
import androidx.room.Room
import mx.edu.utez.ejercicios.db.Db
import mx.edu.utez.ejercicios.shared.Shared

/**
 * Es importada en el AndroidManifest.xml*/
class EjerciciosApplication: Application() {
    companion object {
        lateinit var room: Db
        lateinit var shared: Shared
    }

    override fun onCreate() {
        super.onCreate()
        // Realiza la configuración iniciar para usar esta variable desde cualquier Actividad
        room = Room.databaseBuilder(
            applicationContext, // Contexto de la aplicación
            Db::class.java,     // Clase que contiene la base de datos
            "dmi_10a"     // Nombre de la base de datos
        ).build()

        shared = Shared(applicationContext)
    }

}