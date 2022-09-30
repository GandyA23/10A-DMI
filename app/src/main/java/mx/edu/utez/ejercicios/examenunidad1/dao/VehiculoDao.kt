package mx.edu.utez.ejercicios.examenunidad1.dao

import androidx.room.*
import mx.edu.utez.ejercicios.examenunidad1.entity.VehiculoEntity

@Dao
interface VehiculoDao {
    @Insert
    suspend fun insert(vehiculo: VehiculoEntity)

    @Query("SELECT * FROM vehiculo")
    suspend fun getAll() : List<VehiculoEntity>
}