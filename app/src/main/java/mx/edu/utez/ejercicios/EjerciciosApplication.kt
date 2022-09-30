package mx.edu.utez.ejercicios

import android.app.Application
import androidx.room.Room
import mx.edu.utez.ejercicios.db.Db
import mx.edu.utez.ejercicios.shared.Shared

class EjerciciosApplication: Application() {
    companion object {
        lateinit var room: Db
        lateinit var shared: Shared
    }

    override fun onCreate() {
        super.onCreate()
        room = Room.databaseBuilder(
            applicationContext,
            Db::class.java,
            "aavila"
        ).build()

        shared = Shared(applicationContext)
    }

}