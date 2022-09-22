package mx.edu.utez.ejercicios.db

import androidx.room.*

/**
 * Room necesita de un Entity y un empleado para realizar operaciones
 * */
@Dao
interface EmpleadoDao {
    @Insert
    suspend fun insert(empleado: EmpleadoEntity)

    @Query("SELECT * FROM empleado")
    suspend fun getAll() : List<EmpleadoEntity>

    @Delete
    suspend fun delete(empleado: EmpleadoEntity)

    @Update
    suspend fun update(empleado: EmpleadoEntity)

    // Uso de parámetros en @Query
    @Query("SELECT * FROM empleado WHERE id = :id")
    suspend fun getById(id: Int) : EmpleadoEntity
}