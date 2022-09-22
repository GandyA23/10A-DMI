package mx.edu.utez.ejercicios.db

import androidx.room.*

/**
 * Room necesita de un Entity y un empleado para realizar operaciones
 * */
@Dao
interface EmpleadoDao {
    @Insert
    fun insert(empleado: EmpleadoEntity)

    @Query("SELECT * FROM empleado")
    fun getAll() : List<EmpleadoEntity>

    @Delete
    fun delete(empleado: EmpleadoEntity)

    @Update
    fun update(empleado: EmpleadoEntity)

    // Uso de parámetros en @Query
    @Query("SELECT * FROM empleado WHERE id = :id")
    fun getById(id: Int) : EmpleadoEntity
}