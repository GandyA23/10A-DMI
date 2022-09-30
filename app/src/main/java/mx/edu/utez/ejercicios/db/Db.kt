package mx.edu.utez.ejercicios.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.edu.utez.ejercicios.examenunidad1.dao.VehiculoDao
import mx.edu.utez.ejercicios.examenunidad1.entity.VehiculoEntity

/**
 * Esta clase se encarga de crear las tablas y generar las instancias
 * */
// Anotación necesaria para indicar que esta clase es la DB
@Database(
    entities = [
        // Aquí van todas las entidades
        EmpleadoEntity::class,
        VehiculoEntity::class
    ],

    // Es necesario actualizar la versión de DB cuando se realizan cambios en los Entities (producción)
    version = 1
)
abstract class Db: RoomDatabase() {
    // Se debe de importar un dao por cada tabla para utilizar sus operaciones
    abstract fun getEmpleadoDao(): EmpleadoDao
    abstract fun getVehiculoDao(): VehiculoDao
}